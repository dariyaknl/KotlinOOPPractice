open class Vehicle(val brand: String) {
    init {
        require(brand.isNotBlank()) { "Brand cannot be blank" }
    }

    open fun info(): String = "Vehicle brand=$brand"
    open fun maxSpeedKmh(): Int = 100
}

class Car(brand: String, val doors: Int) : Vehicle(brand) {
    init {
        require(doors >= 2) { "Car must have at least 2 doors" }
    }

    override fun info(): String = "Car $brand, doors=$doors"
    override fun maxSpeedKmh(): Int = 200
}

class Bike(brand: String, val gears: Int) : Vehicle(brand) {
    init {
        require(gears >= 1) { "Bike must have at least 1 gear" }
    }

    override fun info(): String = "Bike $brand, gears=$gears"
    override fun maxSpeedKmh(): Int = 35
}

fun main() {
    val car = Car("Volvo", 4)
    val bike = Bike("Trek", 18)

    println("${car.info()}, Max Speed: ${car.maxSpeedKmh()} km/h")
    println("${bike.info()}, Max Speed: ${bike.maxSpeedKmh()} km/h")
}