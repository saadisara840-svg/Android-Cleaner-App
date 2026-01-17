package com.example.applicationdenettoyage

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun clickCleanButton_opensCleaningActivity() {
        onView(withId(R.id.btnClean))
            .perform(click())

        Intents.intended(
            hasComponent(CleaningActivity::class.java.name)
        )
    }
    @Test
    fun clickStartAutoClean_changesText() {
        onView(withId(R.id.btnStartAuto)).perform(click())
        onView(withId(R.id.txtResult))
            .check(matches(withText("Auto nettoyage activé")))
    }

    @Test
    fun clickStopAutoClean_changesText() {
        onView(withId(R.id.btnStopAuto)).perform(click())
        onView(withId(R.id.txtResult))
            .check(matches(withText("Auto nettoyage arrêté")))
    }

}
