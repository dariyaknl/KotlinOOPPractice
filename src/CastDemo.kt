fun doorsOrZero(v: Vehicle): Int {
    return if (v is Car) {
        v.doors // Smart cast
    } else {
        0
    }
}

fun main() {
    val car: Vehicle = Car("Audi", 4)
    val bike: Vehicle = Bike("Giant", 21)

    println("Car doors: ${doorsOrZero(car)}")
    println("Bike doors: ${doorsOrZero(bike)}")

    println("\n--- Safe & Unsafe Cast Demo ---")
    val c: Car? = bike as? Car
    if (c == null) {
        println("Safe downcast (bike as? Car) returned null as expected.")
    } else {
        println("Car doors: ${c.doors}")
    }
}
