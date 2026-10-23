open class Ticket(val code: String, val basePrice: Int) {
    init {
        require(code.isNotBlank()) { "Ticket code cannot be blank" }
        require(basePrice >= 0) { "Base price cannot be negative" }
    }

    open fun price(): Int = basePrice
}

class AdultTicket(code: String, basePrice: Int) : Ticket(code, basePrice)

class ChildTicket(code: String, basePrice: Int, val guardianCode: String) : Ticket(code, basePrice) {
    init {
        require(guardianCode.isNotBlank()) { "Guardian code required" }
    }

    override fun price(): Int = super.price() / 2
}

class VipTicket(code: String, basePrice: Int, val loungeAccess: Boolean) : Ticket(code, basePrice) {
    override fun price(): Int = super.price() + if (loungeAccess) 50 else 20
}

fun checkout(tickets: List<Ticket>): Int {
    return tickets.sumOf { it.price() }
}

fun childGuardianCodes(tickets: List<Ticket>): List<String> {
    val result = mutableListOf<String>()
    for (t in tickets) {
        if (t is ChildTicket) {
            result.add(t.guardianCode)
        }
    }
    return result
}

fun main() {
    val tickets: List<Ticket> = listOf(
        AdultTicket("A-101", 100),
        ChildTicket("C-102", 100, guardianCode = "G-1"),
        VipTicket("V-103", 100, loungeAccess = true),
        ChildTicket("C-104", 100, guardianCode = "G-9")
    )

    println("total=${checkout(tickets)}")
    println("guardians=${childGuardianCodes(tickets).joinToString(", ")}")
}