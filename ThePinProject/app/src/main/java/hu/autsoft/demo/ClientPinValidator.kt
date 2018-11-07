package hu.autsoft.demo

import android.support.annotation.StringRes

class ClientPinValidator {

    sealed class ValidationResult {
        object Success : ValidationResult()
        class Error(@StringRes val messageId: Int) : ValidationResult()
    }

    fun validatePin(pin: String): ValidationResult {
        if (!pin.matches(Regex("\\d+"))) {
            return ValidationResult.Error(R.string.pin_not_numeric)
        }
        if (pin == "7878") {
            return ValidationResult.Success
        }
        return ValidationResult.Error(R.string.pin_invalid)
    }

}