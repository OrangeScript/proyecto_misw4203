package com.example.vinilos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.vinilos.R
import com.example.vinilos.databinding.ActivityMainBinding
import com.example.vinilos.ui.Opciones.Opciones
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        replaceFragment(ListaAlbum())

        val navBar = findViewById<NavigationBarView>(R.id.nav_view)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_activity_main) as NavHostFragment

        navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
