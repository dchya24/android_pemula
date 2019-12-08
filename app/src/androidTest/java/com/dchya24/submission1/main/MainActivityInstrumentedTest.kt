package com.dchya24.submission1.main

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dchya24.submission1.R.id.*
import com.dchya24.submission1.search.SearchActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @Rule
    @JvmField var intentsTestRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun testSearchBehavior(){
        onView(withId(search)).perform(click())

        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("Barcelona vs"), pressImeActionButton())

        intended(hasComponent(SearchActivity::class.java.name))

        onView(withId(pbSearchMatch))
            .check(matches(isDisplayed()))

        Thread.sleep(5000)
        onView(withId(rvMatchList))
            .check(matches(isDisplayed()))

        onView(withId(rvMatchList))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))


        onView(withId(etSearch))
            .perform(clearText())
        onView(withId(etSearch))
            .perform(typeText("Real Madrid vs"))

        onView(withId(btnSearch))
            .perform(click())

        Thread.sleep(5000)

    }
}

