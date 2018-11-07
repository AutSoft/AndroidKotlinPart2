package hu.autsoft.demo.ui.pin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hu.autsoft.demo.ClientPinValidator
import hu.autsoft.demo.Injection
import hu.autsoft.demo.MainActivity
import hu.autsoft.demo.R
import kotlinx.android.synthetic.main.activity_pin.*
import kotlinx.android.synthetic.main.content_pin.*

class PinActivity : AppCompatActivity(), PinView {

    private lateinit var pinPresenter: PinPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)
        setSupportActionBar(toolbar)

        pinPresenter = PinPresenter(
            ClientPinValidator(),
            Injection.provideValidatorApi()
        )
        pinPresenter.attachView(this)

        submitPin.setOnClickListener {
            val pin = pinInputField.text.toString()
            pinPresenter.validatePin(pin)
        }
    }

    override fun onStop() {
        super.onStop()
        pinPresenter.detachView()
    }

    override fun navigateToMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun showError(messageId: Int) {
        pinInputContainer.error = getString(R.string.pin_invalid)
    }

}

