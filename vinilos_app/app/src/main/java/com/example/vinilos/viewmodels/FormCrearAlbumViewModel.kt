package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.vinilos.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class FormCrearAlbumViewModel(application: Application): AndroidViewModel(application){

    private val albumRepository = AlbumRepository(application)

    private val _saveSuccess = MutableLiveData<Boolean>()
    val saveSuccess: LiveData<Boolean>
        get() = _saveSuccess

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private fun postCreateAlbum(albumName: String,
                                albumCover: String,
                                albumReleaseDate: String,
                                albumGenre: String,
                                albumRecordLabel: String,
                                albumDescription: String) {
        val albumData = JSONObject().apply {
            put("name", albumName)
            put("cover", albumCover)
            put("releaseDate", albumReleaseDate)
            put("description", albumDescription)
            put("genre", albumGenre)
            put("recordLabel", albumRecordLabel)
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = albumRepository.createAlbum(albumData)
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
                _saveSuccess.postValue(true)
            } catch (e: Exception) {
                _eventNetworkError.postValue(true)
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    fun onSavedAlbumHandled() {
        _saveSuccess.value = false
    }

    fun getAlbumData(albumName: String, albumCover: String, albumReleaseDate: String, albumGenre: String, albumRecordLabel: String, albumDescription: String) {
        postCreateAlbum(albumName, albumCover, albumReleaseDate, albumGenre, albumRecordLabel, albumDescription)
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FormCrearAlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FormCrearAlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}