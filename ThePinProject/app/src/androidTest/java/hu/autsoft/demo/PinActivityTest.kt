package hu.autsoft.demo

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import hu.autsoft.demo.ui.pin.PinActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PinActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(PinActivity::class.java)

    @Test
    fun givenCorrectPin_whenWeTypeAndSubmit_thenHomeScreenShouldShown() {
        val pin = "7878"

        onView(withId(R.id.pinInputField))
            .perform(typeText(pin))
            .perform(closeSoftKeyboard())
        onView(withId(R.id.submitPin))
            .perform(click())

        onView(withText(R.string.home_successful_login))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenIncorrectPin_whenWeTypeAndSubmit_thenErrorMessageShouldShown() {
        val pin = "1234"

        onView(withId(R.id.pinInputField))
            .perform(typeText(pin))
            .perform(closeSoftKeyboard())
        onView(withId(R.id.submitPin))
            .perform(click())

        onView(withText(R.string.pin_invalid))
            .check(matches(isDisplayed()))
    }

}