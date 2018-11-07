package hu.autsoft.demo

import android.os.Handler
import hu.autsoft.demo.ui.pin.PinValidatorApi
import kotlin.random.Random

class NetworkPinValidator : PinValidatorApi {

    override fun validatePin(pin: String, resultCallback: (Boolean) -> Unit) {
        Handler().postDelayed({
            resultCallback.invoke(Random.nextBoolean())
        }, Random.nextLong(500, 1000))
    }

}
