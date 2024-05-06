package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Coleccionista
import com.example.vinilos.network.NetworkServiceAdapter

class ColeccionistaRepository (val application: Application){
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)

    suspend fun refreshDataCollectors(): List<Coleccionista> {
        return networkServiceAdapter.getColeccionistas()
    }
}