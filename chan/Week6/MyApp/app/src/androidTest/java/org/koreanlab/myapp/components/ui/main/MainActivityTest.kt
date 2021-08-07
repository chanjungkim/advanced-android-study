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

@LargeTest // UI 테스트는 LargeTest이다. 모든 리소스(DB, Network 등 모든 컴포넌트를 사용하는 테스트임을 나타내며, 1000ms 이상 걸리는 테스트)
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    // 시나리오 객체를 정의해준다.
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        // 시나리오 객체를 생성한다.
        scenario = ActivityScenario.launch(MainActivity::class.java) // 해당 Activity를 실행한다.
        scenario.moveToState(Lifecycle.State.RESUMED) // 해당 Activity의 특정 lifecycle로 전환한다.
    }

    @Test
    fun testAddMemo() {
        onView(withId(R.id.et_memo)).perform(ViewActions.typeText("myMemo")) // EditText에서 typing을 한다.
        Espresso.closeSoftKeyboard() // 소프트키보드를 숨긴다.
        onView(withId(R.id.btn_add)).perform(click()) // button을 클릭한다.

        // 동일 작업1
        onView(withId(R.id.et_memo)).perform(ViewActions.typeText("myMemo2"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btn_add)).perform(click())

        // 동일 작업2
        onView(withId(R.id.et_memo)).perform(ViewActions.typeText("myMemo3"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btn_add)).perform(click())

        // RecyclerView의 아이템 갯수를 check한다.
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
