package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Album
import com.example.vinilos.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {

        NetworkServiceAdapter.getInstance(application).getAlbums({

            callback(it)
        },
            onError
        )
    }
}