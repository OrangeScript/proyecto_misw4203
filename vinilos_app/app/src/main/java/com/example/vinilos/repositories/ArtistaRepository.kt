package com.example.vinilos.repositories

import android.app.Application
import android.util.Log
import com.example.vinilos.modelos.Banda
import com.example.vinilos.modelos.Artista
import com.example.vinilos.modelos.Musico
import com.example.vinilos.network.CacheManager
import com.example.vinilos.network.NetworkServiceAdapter

class ArtistaRepository (val application: Application){
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    private val cacheManager = CacheManager.getInstance(application.applicationContext)

    suspend fun refreshDataBandas(): List<Artista>? {
        val potentialResp = cacheManager.getBandas()
        if (potentialResp?.isEmpty() == true) {
            Log.d("Cache decision", "get bandas from network")
            val bandas = networkServiceAdapter.getBandas()
            CacheManager.getInstance(application.applicationContext).addBandas(bandas)
            return bandas
        }
        else {
            Log.d("Cache decision", "return ${potentialResp?.size} bandas from cache")
            return potentialResp
        }
    }

    suspend fun refreshDataBanda(id: Int): Artista? {
        val potentialResp = cacheManager.getBandaDetails(id)
        if (potentialResp == null) {
            Log.d("Cache decision", "get banda from network")
            val banda = networkServiceAdapter.getBanda(id)
            CacheManager.getInstance(application.applicationContext).addBandaDetails(id, banda)
            return banda
        }
        else {
            Log.d("Cache decision", "return banda from cache")
            return potentialResp
        }
    }

    suspend fun refreshDataMusicos(): List<Artista>? {
        val potentialResp = cacheManager.getMusicos()
        if (potentialResp?.isEmpty() == true) {
            Log.d("Cache decision", "get musicos from network")
            val musicos = networkServiceAdapter.getMusicos()
            CacheManager.getInstance(application.applicationContext).addMusicos(musicos)
            return musicos
        }
        else {
            Log.d("Cache decision", "return ${potentialResp?.size} musicos from cache")
            return potentialResp
        }
    }

    suspend fun refreshDataMusico(id: Int): Artista? {
        val potentialResp = cacheManager.getMusicoDetails(id)
        if (potentialResp == null) {
            Log.d("Cache decision", "get musico from network")
            val musico = networkServiceAdapter.getMusico(id)
            CacheManager.getInstance(application.applicationContext).addMusicoDetails(id, musico)
            return musico
        }
        else {
            Log.d("Cache decision", "return musico from cache")
            return potentialResp
        }
    }
}