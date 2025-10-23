package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new
            ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testAddCity(){
// Click on Add City button
        onView(withId(R.id.button_add)).perform(click());
// Type "Edmonton" in the editText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
// Click on Confirm
        onView(withId(R.id.button_confirm)).perform(click());
// Check if text "Edmonton" is matched with any of the text displayed on the screen
        onView(withText("Edmonton")).check(matches(isDisplayed()));
    }

    @Test
    public void testClearCity(){
// Add first city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());
//Add another city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Vancouver"));
        onView(withId(R.id.button_confirm)).perform(click());
//Clear the list
        onView(withId(R.id.button_clear)).perform(click());
        onView(withText("Edmonton")).check(doesNotExist());
        onView(withText("Vancouver")).check(doesNotExist());
    }

    @Test
    public void testListView(){
        //Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        //Check if in the AdapterView (given id of that adapter view), there is data
        //which is an instance of String located at position zero
        //If this data matches the text we provided, then test passes
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0)
                .check(matches((withText("Edmonton"))));
    }

    @Test
    public void testShowActivity(){
        //Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        //Click the city to launch activity
        onData(is("Edmonton")).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        //Check if activity launched by checking cityText which is unique to ShowActivity
        onView(withId(R.id.cityText)).check(matches(withText("Edmonton")));
        //Check if we're not in MainActivity by trying to find a button unique to it
        //city_list should not exist
        onView(withId(R.id.city_list)).check(doesNotExist());

//        go back to main activity
        onView(withId(R.id.goBackBtn)).perform(click());
//        check if data is persistent
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0)
                .check(matches((withText("Edmonton"))));


    }

}




