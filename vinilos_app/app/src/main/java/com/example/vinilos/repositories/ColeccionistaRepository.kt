package com.example.vinilos.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Coleccionista
import com.example.vinilos.network.CacheManager
import com.example.vinilos.network.NetworkServiceAdapter

class ColeccionistaRepository (val application: Application){
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    private val cacheManager = CacheManager.getInstance(application.applicationContext)

    suspend fun refreshDataCollectors(): List<Coleccionista>? {
        val potentialResp = cacheManager.getCollectors()
        if (potentialResp?.isEmpty() == true) {
            Log.d("Cache decision", "get collectors from network")
            val collectors = networkServiceAdapter.getColeccionistas()
            CacheManager.getInstance(application.applicationContext).addCollectors(collectors)
            return collectors
        }
        else {
            Log.d("Cache decision", "return ${potentialResp?.size} collectors from cache")
            return potentialResp
        }
    }

}