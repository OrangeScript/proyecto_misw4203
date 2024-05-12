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
public class testColeccionistas {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void mainActivityTest5() throws InterruptedException {

        ViewInteraction listaAlbumExiste = onView(allOf(withId(R.id.listaAlbum), isDisplayed()));

        ViewInteraction clickListaColeccionistas = onView(allOf(withId(R.id.listaColeccionista))).perform(click());

        ViewInteraction listaColeccionistaExiste = onView(allOf(withId(R.id.listaColeccionista), isDisplayed()));

        TimeUnit.SECONDS.sleep(1);

        //Verificar tarjeta coleccionista
        ViewInteraction nombreColeccionistaExiste = onView(allOf(withId(R.id.coleccionistaName), isDisplayed()));

        ViewInteraction imagenColeccionistaExiste = onView(allOf(withId(R.id.coleccionistaImage), isDisplayed()));

        //Verificar detalle coleccionista en tarjeta
        ViewInteraction clickPrimerColeccionista = onView(withId(R.id.recyclerViewColeccionista)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        ViewInteraction mailColeccionistaExiste = onView(allOf(withId(R.id.coleccionistaMail), isDisplayed()));

        ViewInteraction telefonoColeccionistaExiste = onView(allOf(withId(R.id.coleccionistaTelefono), isDisplayed()));
    }
}
