package itp341.verduzco.salvador.usclassifieds;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CategoryBooksTest {

    @Rule
    public ActivityTestRule<LoginPage> mActivityTestRule = new ActivityTestRule<>(LoginPage.class);

    @Test
    public void categoryBooksTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.LoggingIn),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.radio_books), withText("Books"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        // Make sure firebase fetches the data
        try { Thread.sleep(4000); } catch (InterruptedException ie) { }

        DataInteraction frameLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listView),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                5)))
                .atPosition(0);
        frameLayout.perform(click());

        // Make sure firebase fetches the data
        try { Thread.sleep(4000); } catch (InterruptedException ie) { }

        ViewInteraction textView = onView(
                allOf(withId(R.id.category_text),
                        childAtPosition(
                                allOf(withId(R.id.GridLayout1),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                9),
                        isDisplayed()));

        // Make sure firebase fetches the data
        try { Thread.sleep(4000); } catch (InterruptedException ie) { }
        textView.check(matches(withText("Books")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}