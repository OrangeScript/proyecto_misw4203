package com.example.vinilos;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.vinilos.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class testDetalleAlbum {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest3() {
        ViewInteraction listaAlbumExiste = onView(withId(R.id.listaAlbum));

        ViewInteraction clickPrimerAlbum = onView(withId(R.id.AlbumCardView)).perform();

        //ViewInteraction clickPrimerElemento = onView(ViewMatchers.withId(R.id.listaAlbum)).perform(ViewActions.click());

        //ViewInteraction detalleAlbumExiste =
    }
}


