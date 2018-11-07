package hu.autsoft.demo.ui.pin

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import hu.autsoft.demo.ClientPinValidator
import hu.autsoft.demo.MockPinValidator
import hu.autsoft.demo.R
import org.junit.Before
import org.junit.Test

class PinPresenterTest {

    private lateinit var pinPresenter: PinPresenter

    private var pinView = mock<PinView>()

    @Before
    fun setUp() {
        pinPresenter = PinPresenter(ClientPinValidator(), MockPinValidator())
        pinPresenter.attachView(pinView)
    }

    @Test
    fun `Given invalid pin, when we validate it, showError should be called`() {
        val pin = "2424"

        pinPresenter.validatePin(pin)

        verify(pinView).showError(R.string.pin_invalid)
    }

    @Test
    fun `Given valid pin, when we validate it, navigateToMainScreen should be called`() {
        val pin = "7878"

        pinPresenter.validatePin(pin)

        verify(pinView).navigateToMainScreen()
    }

}