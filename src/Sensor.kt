class Sensor(val id: String, var reading: Double) {
    init {
        require(id.isNotBlank()) { "ID cannot be blank" }
        require(reading.isFinite()) { "Initial reading must be finite" }
    }

    fun update(newReading: Double) {
        require(newReading.isFinite()) { "Reading must be a finite number" }
        reading = newReading
    }

    override fun toString(): String = "Sensor(id=$id, reading=$reading)"
}

fun main() {
    val s1 = Sensor("TEMP-01", 22.5)
    val s2 = Sensor("HUM-02", 45.0)

    println("Before update:")
    println(s1)
    println(s2)

    s1.update(24.8)

    println("\nAfter update:")
    println(s1)
    println(s2)
}