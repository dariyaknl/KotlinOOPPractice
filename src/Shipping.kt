open class Parcel(val id: String, val weightKg: Double) {
    init {
        require(id.isNotBlank()) { "Parcel ID cannot be blank" }
        require(weightKg > 0.0) { "Weight must be positive" }
    }

    open fun shippingCost(): Double = weightKg * 2.0
}

class ExpressParcel(id: String, weightKg: Double) : Parcel(id, weightKg) {
    override fun shippingCost(): Double = super.shippingCost() + 15.0
}

class EconomyParcel(id: String, weightKg: Double) : Parcel(id, weightKg) {
    override fun shippingCost(): Double = super.shippingCost() * 0.8
}

fun totalShipping(parcels: List<Parcel>): Double {
    return parcels.sumOf { it.shippingCost() }
}

fun main() {
    val parcels: List<Parcel> = listOf(
        Parcel("P1", 5.0),
        ExpressParcel("P2", 5.0),
        EconomyParcel("P3", 5.0)
    )

    parcels.forEach { p ->
        println("${p.id}: $%.2f".format(p.shippingCost()))
    }

    val total = totalShipping(parcels)
    println("Total shipping cost: $%.2f".format(total))
}