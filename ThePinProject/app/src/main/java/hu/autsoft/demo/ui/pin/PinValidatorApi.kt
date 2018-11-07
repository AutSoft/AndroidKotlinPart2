package hu.autsoft.demo.ui.pin

interface PinValidatorApi {

    fun validatePin(pin: String, resultCallback: (Boolean) -> Unit)

}