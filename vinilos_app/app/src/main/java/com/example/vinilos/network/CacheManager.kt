package com.example.vinilos.network

import android.content.Context
import com.example.vinilos.modelos.Album
import com.example.vinilos.modelos.Artista
import com.example.vinilos.modelos.Coleccionista

class CacheManager {
    companion object {
        private var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager().also {
                    instance = it
                }
            }
    }

    private var albums: List<Album>? = listOf()
    private var bandas: List<Artista>? = listOf()
    private var musicos: List<Artista>? = listOf()
    private var collectors: List<Coleccionista>? = listOf()
    private var albumsDetails: HashMap<Int, Album> = hashMapOf()
    private var bandasDetails: HashMap<Int, Artista> = hashMapOf()
    private var musicosDetails: HashMap<Int, Artista> = hashMapOf()

    fun getAlbums(): List<Album>? {
        return albums
    }

    fun addAlbums(newAlbums: List<Album>) {
        albums = newAlbums
    }

    fun getBandas(): List<Artista>? {
        return bandas
    }

    fun addBandas(newBandas: List<Artista>) {
        bandas = newBandas
    }

    fun getMusicos(): List<Artista>? {
        return musicos
    }

    fun addMusicos(newMusicos: List<Artista>) {
        musicos = newMusicos
    }

    fun getCollectors(): List<Coleccionista>? {
        return collectors
    }

    fun addCollectors(newCollectors: List<Coleccionista>) {
        collectors = newCollectors
    }

    fun getAlbumDetails(id: Int): Album? {
        return if (albumsDetails.containsKey(id)) {
            albumsDetails[id]!!
        } else {
            null
        }
    }

    fun addAlbumDetails(id: Int, album: Album) {
        if (!albumsDetails.containsKey(id)) {
            albumsDetails[id] = album
        }
    }

    fun getBandaDetails(id: Int): Artista? {
        return if (bandasDetails.containsKey(id)) {
            bandasDetails[id]!!
        } else {
            null
        }
    }

    fun addBandaDetails(id: Int, banda: Artista) {
        if (!bandasDetails.containsKey(id)) {
            bandasDetails[id] = banda
        }
    }

    fun getMusicoDetails(id: Int): Artista? {
        return if (musicosDetails.containsKey(id)) {
            musicosDetails[id]!!
        } else {
            null
        }
    }

    fun addMusicoDetails(id: Int, musico: Artista) {
        if (!musicosDetails.containsKey(id)) {
            musicosDetails[id] = musico
        }
    }

}
