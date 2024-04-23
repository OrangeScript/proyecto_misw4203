package com.example.vinilos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vinilos.ui.DetalleAlbum.DetalleAlbum
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navBar = findViewById<NavigationBarView>(R.id.nav_view)

        navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fragment_detalle_album -> setCurrentFragment(DetalleAlbum())
                R.id.fragment_lista_album -> setCurrentFragment(DetalleAlbum())
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_activity_main, fragment)
            commit()
        }
}
