package com.example.vinilos.repositories

import android.app.Application
import android.util.Log
import com.example.vinilos.modelos.Album
import com.example.vinilos.network.CacheManager
import com.example.vinilos.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    private val cacheManager = CacheManager.getInstance(application.applicationContext)

    suspend fun refreshDataAlbums(): List<Album>? {
        val potentialResp = cacheManager.getAlbums()
        if (potentialResp?.isEmpty() == true) {
            Log.d("Cache decision", "get albums from network")
            val albums = networkServiceAdapter.getAlbums()
            CacheManager.getInstance(application.applicationContext).addAlbums(albums)
            return albums
        }
        else {
            Log.d("Cache decision", "return ${potentialResp?.size} albums from cache")
            return potentialResp
        }
    }

    suspend fun refreshDataAlbum(id: Int): Album {
        val potentialResp = cacheManager.getAlbumDetails(id)
        if (potentialResp == null) {
            Log.d("Cache decision", "get album from network")
            val album = networkServiceAdapter.getAlbum(id)
            CacheManager.getInstance(application.applicationContext).addAlbumDetails(id, album)
            return album
        }
        else {
            Log.d("Cache decision", "return album from cache")
            return potentialResp
        }
    }

}