open class Employee(val name: String, val baseSalary: Int) {
    init {
        require(name.isNotBlank()) { "Name cannot be blank" }
        require(baseSalary >= 0) { "Base salary cannot be negative" }
    }

    open fun pay(): Int = baseSalary
    open fun describe(): String = "$name: ${pay()}"
}

class Accountant(name: String, baseSalary: Int, val bonusPercent: Int) : Employee(name, baseSalary) {
    init {
        require(bonusPercent in 0..100) { "Bonus percent must be between 0 and 100" }
    }

    override fun pay(): Int {
        val bonus = (super.pay() * bonusPercent) / 100
        return super.pay() + bonus
    }

    override fun describe(): String = "Accountant " + super.describe()
}

class Intern(name: String, baseSalary: Int, val mentor: String) : Employee(name, baseSalary) {
    init {
        require(mentor.isNotBlank()) { "Mentor name cannot be blank" }
    }

    override fun pay(): Int = super.pay() / 2
    override fun describe(): String = "Intern ${super.describe()} (Mentor: $mentor)"
}

fun main() {
    val employees: List<Employee> = listOf(
        Employee("Alice", 3000),
        Accountant("Bob", 4000, 15),
        Intern("Charlie", 2000, "Alice")
    )

    println("--- Payroll List ---")
    var totalPay = 0
    for (emp in employees) {
        println(emp.describe())
        totalPay += emp.pay()
    }
    println("Total Payroll: $totalPay")
}