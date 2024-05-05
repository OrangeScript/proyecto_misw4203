package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Banda
import com.example.vinilos.modelos.Musico
import com.example.vinilos.network.NetworkServiceAdapter

class ArtistaRepository (val application: Application){
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)

    suspend fun refreshDataBandas(): List<Banda> {
        return networkServiceAdapter.getBandas()
    }

    suspend fun refreshDataBanda(id: Int): Banda {
        return networkServiceAdapter.getBanda(id)
    }

    suspend fun refreshDataMusicos(): List<Musico> {
        return networkServiceAdapter.getMusicos()
    }

    suspend fun refreshDataMusico(id: Int): Musico {
        return networkServiceAdapter.getMusico(id)
    }
}