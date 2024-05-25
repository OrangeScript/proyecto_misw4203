package com.example.vinilos;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.object.HasToString.hasToString;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
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
public class testCrearAlbum {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest7() throws InterruptedException {

        String nombrealbum = "Evangelion";
        String imagenPortada = "https://en.wikipedia.org/wiki/Evangelion_%28album%29#/media/File:Behemoth_-_Evangelion.jpg";
        String fechaLanzamiento = "2009-08-07";
        String tesxtoDescripcionAlbum = "Audentis fortuna iuvat";

        ViewInteraction NavegarOpciones = onView(allOf(withId(R.id.opciones))).perform(click());

        TimeUnit.SECONDS.sleep(1);

        ViewInteraction clickCrearAlbum = onView(allOf(withId(R.id.opciones_crear_album_btn))).perform(click());

        TimeUnit.SECONDS.sleep(1);

        ViewInteraction nombreAlbum = onView(allOf(withId(R.id.nombreTitulo), isDisplayed()));

        ViewInteraction inputNombreAlbum = onView(allOf(withId(R.id.inputCrearAlbumNombre), isDisplayed()));

        ViewInteraction escribirNombreAlbum = onView(withId(R.id.inputCrearAlbumNombre)).perform(typeText(nombrealbum), closeSoftKeyboard());

        ViewInteraction linkImagenPortadaAlbum = onView(allOf(withId(R.id.imagenCrearAlbum), isDisplayed()));

        ViewInteraction inputImagenPortadaAlbum = onView(allOf(withId(R.id.inputCrearAlbumImagen), isDisplayed()));

        ViewInteraction escribirImagenPortadaAlbum = onView(withId(R.id.inputCrearAlbumImagen)).perform(typeText(imagenPortada), closeSoftKeyboard());

        ViewInteraction fechaAlbum = onView(allOf(withId(R.id.fechaCrearAlbum), isDisplayed()));

        ViewInteraction inputFechaAlbum = onView(allOf(withId(R.id.inputCrearAlbumFecha), isDisplayed()));

        ViewInteraction escribirFechaAlbum = onView(withId(R.id.inputCrearAlbumFecha)).perform(typeText(fechaLanzamiento), closeSoftKeyboard());

        ViewInteraction generoAlbum = onView(allOf(withId(R.id.generoCrearAlbum), isDisplayed()));

        ViewInteraction dropdownCrearAlbumGenero = onView(allOf(withId(R.id.dropdownCrearAlbumGenero), isDisplayed()));

        ViewInteraction clickdropdownCrearAlbumGenero = onView(withId(R.id.dropdownCrearAlbumGenero)).perform(click());

        ViewInteraction seleccionarGenero = onData(hasToString("Rock")).perform(click());

        ViewInteraction disqueraAlbum = onView(allOf(withId(R.id.DisqueraCrearAlbum), isDisplayed()));

        ViewInteraction dropdownCrearAlbumDisquera = onView(allOf(withId(R.id.dropdownCrearAlbumDisquera), isDisplayed()));

        ViewInteraction clickdropdownCrearAlbumDisquera = onView(withId(R.id.dropdownCrearAlbumDisquera)).perform(click());

        ViewInteraction seleccionarDisquera = onData(hasToString("EMI")).perform(click());

        ViewInteraction descripcionAlbum = onView(allOf(withId(R.id.descripcionCrearAlbum), isDisplayed()));

        ViewInteraction inputDescripcionAlbum = onView(allOf(withId(R.id.inputCrearAlbumDescripcion), isDisplayed()));

        ViewInteraction escribirDescripcionAlbum = onView(withId(R.id.inputCrearAlbumDescripcion)).perform(typeText(tesxtoDescripcionAlbum), closeSoftKeyboard());

        TimeUnit.SECONDS.sleep(1);

        ViewInteraction guardarAlbum = onView(withId(R.id.guardarCrearAlbum)).perform(click());

        TimeUnit.SECONDS.sleep(1);

        ViewInteraction popupCreadoCorrectamente = onView(allOf(withText("Álbum creado correctamente"), isDisplayed()));
    }

}
