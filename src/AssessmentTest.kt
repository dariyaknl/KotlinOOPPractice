open class Assessment(val title: String, val maxPoints: Int) {
    init {
        require(title.isNotBlank()) { "Title cannot be blank" }
        require(maxPoints > 0) { "Max points must be > 0" }
    }

    open fun scoreTowardGrade(raw: Int): Double {
        val coerced = raw.coerceIn(0, maxPoints)
        return coerced.toDouble() / maxPoints
    }
}

class Quiz(
    title: String,
    maxPoints: Int,
    val penaltyIfLate: Double
) : Assessment(title, maxPoints) {
    init {
        require(penaltyIfLate in 0.0..0.5) { "Penalty must be in 0.0..0.5" }
    }

    override fun scoreTowardGrade(raw: Int): Double {
        val baseScore = super.scoreTowardGrade(raw)
        return baseScore * (1.0 - penaltyIfLate)
    }
}

class Project(
    title: String,
    maxPoints: Int,
    val bonusCap: Double
) : Assessment(title, maxPoints) {
    init {
        require(bonusCap >= 0.0) { "Bonus cap must be non-negative" }
    }

    override fun scoreTowardGrade(raw: Int): Double {
        val maxAllowedPoints = (maxPoints * (1.0 + bonusCap)).toInt()
        val effectiveRaw = raw.coerceIn(0, maxAllowedPoints)

        return if (effectiveRaw <= maxPoints) {
            super.scoreTowardGrade(effectiveRaw)
        } else {
            effectiveRaw.toDouble() / maxPoints
        }
    }
}

fun main() {
    val assessments: List<Assessment> = listOf(
        Assessment("Midterm Exam", 100),
        Quiz("Quiz 1 (Late)", 20, penaltyIfLate = 0.2),
        Project("Final Project", 100, bonusCap = 0.15)
    )

    val fixedRawScore = 110

    println("Scores for raw input = $fixedRawScore:")
    for (a in assessments) {
        val score = a.scoreTowardGrade(fixedRawScore)
        println("${a.title}: %.2f".format(score))
    }
}