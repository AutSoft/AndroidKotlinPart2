package hu.autsoft.demo.ui.pin

import hu.autsoft.demo.ClientPinValidator
import hu.autsoft.demo.R

class PinPresenter(private val clientPinValidator: ClientPinValidator, private val pinValidatorApi: PinValidatorApi) {

    private var view: PinView? = null

    fun attachView(view: PinView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun validatePin(pin: String) {
        clientPinValidator.validatePin(pin)
        val result = clientPinValidator.validatePin(pin)
        when (result) {
            is ClientPinValidator.ValidationResult.Success -> handleSuccess(pin)
            is ClientPinValidator.ValidationResult.Error -> view?.showError(result.messageId)
        }
    }

    private fun handleSuccess(pin: String) {
        pinValidatorApi.validatePin(pin) { isPinValid ->
            if (isPinValid) {
                view?.navigateToMainScreen()
            } else {
                view?.showError(R.string.pin_invalid)
            }
        }
    }

}