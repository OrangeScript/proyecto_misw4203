package com.example.vinilos.repositories

import android.app.Application
import android.app.SharedElementCallback
import androidx.lifecycle.AndroidViewModel
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Album
import com.example.vinilos.network.NetworkServiceAdapter

class DetalleAlbumRepository (application: Application): AndroidViewModel(application) {
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    fun refreshData(id: Int, onComplete: (resp: Album) -> Unit, onError: (VolleyError)->Unit) {
        networkServiceAdapter.getAlbum(id, onComplete, onError)
    }
}