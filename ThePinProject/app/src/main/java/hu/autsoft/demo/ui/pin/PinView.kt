package hu.autsoft.demo.ui.pin

import android.support.annotation.StringRes

interface PinView {
    fun navigateToMainScreen()
    fun showError(@StringRes messageId: Int)
}