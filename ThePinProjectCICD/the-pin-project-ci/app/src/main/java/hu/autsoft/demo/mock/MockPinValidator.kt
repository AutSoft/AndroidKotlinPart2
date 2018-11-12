package hu.autsoft.demo.mock

import hu.autsoft.demo.ui.pin.PinValidatorApi

class MockPinValidator : PinValidatorApi {

    override fun validatePin(pin: String, resultCallback: (Boolean) -> Unit) {
        resultCallback.invoke(true)
    }

}