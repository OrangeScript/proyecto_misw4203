package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinilos.modelos.Album
import com.example.vinilos.repositories.DetalleAlbumRepository

class DetalleAlbumViewModel(application: Application, albumId: Int) : AndroidViewModel(application) {

    private val DetalleAlbumRepository = DetalleAlbumRepository(application)

    private val _album = MutableLiveData<Album>()

    val albumId : Int = albumId
    val album: LiveData<Album>
        get() = _album

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        DetalleAlbumRepository.refreshData(
            albumId,
            {
                _album.postValue(it)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            },
            {
                _eventNetworkError.value = true
            }
        )
    }
}