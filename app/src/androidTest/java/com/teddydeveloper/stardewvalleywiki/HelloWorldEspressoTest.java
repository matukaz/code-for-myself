package com.teddydeveloper.stardewvalleywiki;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
/**
    @Test
    public void listGoesOverTheFold() {


        onView(withId(R.id.mainActivityTitleText)).check(matches(withText("Welcome to Stardew Valley Wiki")));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

    } */

    @Test
    public void testIsMainListVisible() {

        onView(withId(R.id.scrollableview)).check(matches(isDisplayed()));

    }
}