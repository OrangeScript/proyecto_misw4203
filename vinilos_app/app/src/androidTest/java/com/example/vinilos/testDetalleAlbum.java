package com.example.vinilos;

import androidx.test.espresso.contrib.RecyclerViewActions;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

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
        ViewInteraction listaAlbumExiste = onView(allOf(withId(R.id.listaAlbum), isDisplayed()));

        ViewInteraction clickPrimerAlbum = onView(withId(R.id.recyclerViewAlbums)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        ViewInteraction detalleAlbumExiste = onView(allOf(withId(R.id.detalleAlbum), isDisplayed()));

        ViewInteraction imagenAlbumExiste = onView(allOf(withId(R.id.DetalleAlbumFoto), isDisplayed()));

        ViewInteraction tituloAlbumExiste = onView(allOf(withId(R.id.DetalleAlbumTitulo), isDisplayed()));

        ViewInteraction disqueraAlbumExiste = onView(allOf(withId(R.id.DetalleAlbumDisquera), isDisplayed()));

        ViewInteraction generoAlbumExiste = onView(allOf(withId(R.id.DetalleAlbumGenero), isDisplayed()));

        ViewInteraction lanzamientoAlbumExiste = onView(allOf(withId(R.id.DetalleAlbumLanzamiento), isDisplayed()));

        ViewInteraction descripcionAlbumExiste = onView(allOf(withId(R.id.DetalleAlbumDescripcion), isDisplayed()));
    }
}


