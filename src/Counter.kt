class Counter(start: Int = 0) {
    private var count: Int = start

    fun value(): Int = count

    fun inc(step: Int = 1) {
        require(step > 0) { "Step must be strictly positive (> 0)" }
        count += step
    }

    fun reset() {
        count = 0
    }
}

fun main() {
    val defaultCounter = Counter()
    println("Default counter start: ${defaultCounter.value()}")
    defaultCounter.inc(2)
    println("After inc(2): ${defaultCounter.value()}")

    val customCounter = Counter(10)
    println("Custom counter start: ${customCounter.value()}")
    customCounter.inc()
    println("After default inc(): ${customCounter.value()}")
    customCounter.reset()
    println("After reset: ${customCounter.value()}")
}