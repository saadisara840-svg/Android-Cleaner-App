package com.example.applicationdenettoyage

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CleaningActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(CleaningActivity::class.java)

    @Test
    fun progressBar_isVisible() {
        onView(withId(R.id.progressBar))
            .check(matches(isDisplayed()))
    }

    @Test
    fun txtResult_isDisplayed() {
        onView(withId(R.id.txtResult))
            .check(matches(isDisplayed()))
    }
}

