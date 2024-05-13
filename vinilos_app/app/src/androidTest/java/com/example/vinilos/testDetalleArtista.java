package com.example.vinilos;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
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
public class testDetalleArtista {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest4() throws InterruptedException {
        ViewInteraction listaAlbumExiste = onView(allOf(withId(R.id.listaAlbum), isDisplayed()));

        ViewInteraction clickListaArtistas = onView(allOf(withId(R.id.listaArtista))).perform(click());

        ViewInteraction listaArtistaExiste = onView(allOf(withId(R.id.listaArtista), isDisplayed()));

        TimeUnit.SECONDS.sleep(1);

        ViewInteraction clickPrimerArtista = onView(withId(R.id.recyclerViewArtistas)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        ViewInteraction detalleArtistaExiste = onView(allOf(withId(R.id.detalleArtista), isDisplayed()));

        ViewInteraction imagenArtistaExiste = onView(allOf(withId(R.id.detalleBandaFoto), isDisplayed()));

        ViewInteraction nombreArtistaExiste = onView(allOf(withId(R.id.DetalleBandaNombre), isDisplayed()));

        ViewInteraction descripcionArtistaExiste = onView(allOf(withId(R.id.DetalleBandaDescripcion), isDisplayed()));
    }
}
