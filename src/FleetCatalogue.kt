fun printFleet(fleet: List<Vehicle>) {
    for (v in fleet) {
        println(v.info())
    }
}

fun main() {
    // Explicit upcast
    val v: Vehicle = Car("Volvo", 4)
    println("Polymorphic invocation via base reference 'v':")
    println(v.info())

    println("\n--- Printing Fleet ---")
    val fleet: List<Vehicle> = listOf(
        Car("Volvo", 4),
        Bike("Trek", 18),
        Car("BMW", 2)
    )

    printFleet(fleet)
}