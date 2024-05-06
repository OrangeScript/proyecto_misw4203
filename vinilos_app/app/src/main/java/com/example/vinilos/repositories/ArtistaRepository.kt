package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.modelos.Banda
import com.example.vinilos.modelos.Artista
import com.example.vinilos.modelos.Musico
import com.example.vinilos.network.NetworkServiceAdapter

class ArtistaRepository (val application: Application){
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)

    suspend fun refreshDataBandas(): List<Artista> {
        return networkServiceAdapter.getBandas()
    }

    suspend fun refreshDataBanda(id: Int): Banda {
        return networkServiceAdapter.getBanda(id)
    }

    suspend fun refreshDataMusicos(): List<Artista> {
        return networkServiceAdapter.getMusicos()
    }

    suspend fun refreshDataMusico(id: Int): Musico {
        return networkServiceAdapter.getMusico(id)
    }
}