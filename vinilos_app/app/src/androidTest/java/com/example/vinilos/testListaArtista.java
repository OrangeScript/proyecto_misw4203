package com.example.vinilos;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.vinilos.ui.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class testListaArtista {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest6() {
        ViewInteraction listaAlbumExiste = onView(allOf(withId(R.id.listaAlbum), isDisplayed()));

        ViewInteraction clickListaArtistas = onView(allOf(withId(R.id.listaArtista))).perform(click());

        ViewInteraction coverArtistaExiste = onView(Matchers.allOf(withId(R.id.artistaImage), isDisplayed()));

        ViewInteraction nombreArtistaExiste = onView(Matchers.allOf(withId(R.id.artistaName), isDisplayed()));

    }
}
