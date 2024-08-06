package com.example.monster_arena;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testIntentNavigation() {

        // Sign up for new account
        onView(withId(R.id.signupButton)).perform(click());
        onView(withId(R.id.userNameCreationEditText)).perform(ViewActions.typeText("intentTest"));
        onView(withId(R.id.passwordCreationEditText)).perform(ViewActions.typeText("password"));
        onView(withId(R.id.confirmPasswordCreationEditText)).perform(ViewActions.typeText("password"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signupCreationButton)).perform(click());

        // Log into new account
        onView(withId(R.id.userNameLoginEditText)).perform(ViewActions.typeText("intentTest"));
        onView(withId(R.id.passwordLoginEditText)).perform(ViewActions.typeText("password"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.logoutMenuItem)).perform(click());

        // Log out of new account, then Log into admin
        onView(withText("Logout")).perform(click());
        onView(withId(R.id.userNameLoginEditText)).perform(ViewActions.clearText());
        onView(withId(R.id.passwordLoginEditText)).perform(ViewActions.clearText());
        onView(withId(R.id.userNameLoginEditText)).perform(ViewActions.typeText("admin"));
        onView(withId(R.id.passwordLoginEditText)).perform(ViewActions.typeText("admin"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.manageUsersButton)).perform(click());

        // Grant new account admin
        onView(withText("intentTest")).perform(click());
        onView(withText("Grant Admin")).perform(click());
        onView(withId(R.id.logoutMenuItem)).perform(click());

        // Log out of admin, then log back into new account that now has admin status
        onView(withText("Logout")).perform(click());
        onView(withId(R.id.userNameLoginEditText)).perform(ViewActions.typeText("intentTest"));
        onView(withId(R.id.passwordLoginEditText)).perform(ViewActions.typeText("password"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.manageUsersButton)).perform(click());

        // Using new account, delete an existing account
        onView(withText("testUser")).perform(click());
        onView(withText("Delete")).perform(click());
        onView(withId(R.id.manageUsersButton)).perform(click());

        // There should now be only 2 accounts instead of 3
    }
}