package hu.autsoft.demo

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ClientPinValidatorTest {

    private lateinit var clientPinValidator: ClientPinValidator

    @Before
    fun setUp() {
        clientPinValidator = ClientPinValidator()
    }

    @Test
    fun `Given correct PIN code, when we validate it, success result should be returned`() {
        val pin = "7878"

        val validationResult = clientPinValidator.validatePin(pin)

        assertEquals(ClientPinValidator.ValidationResult.Success, validationResult)
    }

    @Test
    fun `Given incorrect PIN code, when we validate it, invalid error result should be returned`() {
        val pin = "6868"

        val validationResult = clientPinValidator.validatePin(pin)

        assertTrue(validationResult is ClientPinValidator.ValidationResult.Error)
        assertEquals(R.string.pin_invalid, (validationResult as ClientPinValidator.ValidationResult.Error).messageId)
    }

    @Test
    fun `Given not numeric PIN code, when we validate it, not numeric error result should be returned`() {
        val pin = "asdf"

        val validationResult = clientPinValidator.validatePin(pin)

        assertTrue(validationResult is ClientPinValidator.ValidationResult.Error)
        assertEquals(
            R.string.pin_not_numeric,
            (validationResult as ClientPinValidator.ValidationResult.Error).messageId
        )
    }

}