open class Notification(private val payload: String) {
    init {
        require(payload.isNotBlank()) { "Payload cannot be blank" }
    }

    protected fun body(): String = payload
    open fun render(): String = body()
}

class EmailNotification(payload: String, val to: String) : Notification(payload) {
    init {
        require(to.contains("@")) { "Invalid email address" }
    }

    override fun render(): String = "Email to [$to]: ${body()}"
}

class SmsNotification(payload: String, val phone: String) : Notification(payload) {
    init {
        require(phone.isNotBlank()) { "Phone number required" }
    }

    override fun render(): String = "SMS to [$phone]: ${body()}"
}

fun sendAll(items: List<Notification>) {
    for (item in items) {
        println(item.render())
    }
}

fun main() {
    val notifications: List<Notification> = listOf(
        EmailNotification("Your invoice is ready", "user@example.com"),
        SmsNotification("Your OTP code is 4821", "+1234567890"),
        Notification("Generic system alert")
    )

    sendAll(notifications)
}