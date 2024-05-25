package com.example.vinilos;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.vinilos.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class testAsociarTrack {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest6() throws InterruptedException {

        String nombreCancion = "Love of my life";
        String duracionCancion = "03:40";

        ViewInteraction listaAlbumExiste = onView(allOf(withId(R.id.listaAlbum), isDisplayed()));

        TimeUnit.SECONDS.sleep(1);

        ViewInteraction clickPrimerAlbum = onView(withId(R.id.recyclerViewAlbums)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        TimeUnit.SECONDS.sleep(1);

        ViewInteraction clickAsociarTrack = onView(withId(R.id.album_asociar_track_button)).perform(click());

        TimeUnit.SECONDS.sleep(1);

        ViewInteraction nombreAsociarTrackExiste = onView(allOf(withId(R.id.nombreTrackTitulo), isDisplayed()));

        ViewInteraction inputNombreAsociarTrackExiste = onView(allOf(withId(R.id.inputNombreTrack), isDisplayed()));

        ViewInteraction escribirNombreAsociarTrack = onView(withId(R.id.inputNombreTrack)).perform(typeText(nombreCancion), closeSoftKeyboard());

        ViewInteraction duracionAsociarTrackExiste = onView(allOf(withId(R.id.duracionTrackTitulo), isDisplayed()));

        ViewInteraction inputDuracionAsociarTrackExiste = onView(allOf(withId(R.id.inputDuracionTrack), isDisplayed()));

        ViewInteraction escribirDuracionAsociarTrack = onView(withId(R.id.inputDuracionTrack)).perform(typeText(duracionCancion), closeSoftKeyboard());

        TimeUnit.SECONDS.sleep(1);

        ViewInteraction guardarAsociarTrack = onView(withId(R.id.guardarRelacionalTrack)).perform(click());

        TimeUnit.SECONDS.sleep(1);

        ViewInteraction popupCreadoCorrectamente = onView(allOf(withText("Track creado correctamente"), isDisplayed()));

        TimeUnit.SECONDS.sleep(1);

    }

}
