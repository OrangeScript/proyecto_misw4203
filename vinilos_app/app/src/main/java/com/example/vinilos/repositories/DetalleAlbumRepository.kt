package com.example.vinilos.repositories

import android.app.Application
import android.app.SharedElementCallback
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Album
import com.example.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class DetalleAlbumRepository (val application: Application){

    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)

    fun refreshData(AlbumId: String, onComplete: (resp: JSONObject) -> Unit, onError: (VolleyError)->Unit){
        networkServiceAdapter.getAlbum(AlbumId, onComplete,onError)
    }
}