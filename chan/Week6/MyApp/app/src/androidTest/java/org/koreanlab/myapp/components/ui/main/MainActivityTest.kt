package org.koreanlab.myapp.components.ui.main

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.koreanlab.myapp.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testAddMemo() {
        onView(withId(R.id.et_memo)).perform(ViewActions.typeText("myMemo"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btn_add)).perform(click())

        onView(withId(R.id.et_memo)).perform(ViewActions.typeText("myMemo2"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btn_add)).perform(click())

        onView(withId(R.id.et_memo)).perform(ViewActions.typeText("myMemo3"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btn_add)).perform(click())

        onView(withId(R.id.rcv_memo)).check(matches(hasChildCount(3))) // check1
    }

    @Test
    fun testClearAndMemo() {
        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.rcv_memo)).check(matches(hasChildCount(0))) // check2

        onView(withId(R.id.et_memo)).perform(ViewActions.typeText("myMemo4"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btn_add)).perform(click())

        onView(withId(R.id.et_memo)).perform(ViewActions.typeText("myMemo5"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btn_add)).perform(click())

        onView(withId(R.id.rcv_memo)).check(matches(hasChildCount(2))) // check3
        onView(withId(R.id.rcv_memo)).check(matches(isDisplayed())) // check4
    }
}