package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.modelos.Album
import com.example.vinilos.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    suspend fun refreshDataAlbums(): List<Album> {
        return networkServiceAdapter.getAlbums()
    }

    suspend fun refreshDataAlbum(id: Int): Album {
        return networkServiceAdapter.getAlbum(id)
    }
}