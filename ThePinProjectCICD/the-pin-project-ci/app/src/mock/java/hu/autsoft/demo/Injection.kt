package hu.autsoft.demo

import hu.autsoft.demo.mock.MockPinValidator
import hu.autsoft.demo.ui.pin.PinValidatorApi

class Injection {

    companion object {
        fun provideValidatorApi(): PinValidatorApi {
            return MockPinValidator()
        }
    }

}