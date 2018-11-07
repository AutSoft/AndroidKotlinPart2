package hu.autsoft.demo

class Injection {

    companion object {
        fun provideValidatorApi(): PinValidatorApi {
            return NetworkPinValidator()
        }
    }

}