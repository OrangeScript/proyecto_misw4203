package com.example.vinilos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vinilos.R
import com.example.vinilos.databinding.ActivityMainBinding
import com.example.vinilos.ui.Opciones.Opciones
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ListaAlbum())

        val navBar = findViewById<NavigationBarView>(R.id.nav_view)

        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fragment_lista_artista -> replaceFragment(ListaBanda())
                R.id.fragment_lista_album -> replaceFragment(ListaAlbum())
                R.id.fragment_lista_coleccionita -> replaceFragment(ListaColeccionista())
                R.id.fragment_opciones -> replaceFragment(Opciones())
                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_activity_main, fragment)
        fragmentTransaction.commit()
    }
}
