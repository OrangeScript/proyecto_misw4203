package com.example.vinilos;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.vinilos.R;
import com.example.vinilos.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class testListaAlbum {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        ViewInteraction listaAlbumExists = onView(withId(R.id.listaAlbum));

        ViewInteraction elementoAlbumExiste = onView(withId(R.id.AlbumCardView));

        ViewInteraction coverAlbumExiste = onView(withId(R.id.albumCover));

        ViewInteraction nombreAlbumExiste = onView(withId(R.id.albumName));

        ViewInteraction generoAlbumExiste = onView(withId(R.id.albumGenre));

        ViewInteraction fechaSalidaAlbumExiste = onView(withId(R.id.albumReleaseDate));
    }

}
