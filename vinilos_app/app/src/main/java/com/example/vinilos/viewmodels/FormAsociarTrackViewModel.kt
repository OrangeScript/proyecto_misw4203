package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.vinilos.modelos.Album
import com.example.vinilos.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class FormAsociarTrackViewModel(application: Application, albumId: Int) : AndroidViewModel(application) {

    private val albumRepository = AlbumRepository(application)

    val albumId : Int = albumId

    private val _saveSuccess = MutableLiveData<Boolean>()
    val saveSuccess: LiveData<Boolean>
        get() = _saveSuccess

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private fun postTrackToAlbum(albumId: Int, trackName: String, trackDuration: String) {
        val trackData = JSONObject().apply {
            put("name", trackName)
            put("duration", trackDuration)
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = albumRepository.refreshDataTracksAsociados(albumId, trackData)
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

    fun onSavedTrackHandled() {
        _saveSuccess.value = false
    }

    fun getTrackData(trackName: String, trackDuration: String) {
        postTrackToAlbum(albumId, trackName, trackDuration)
    }

    class Factory(val app: Application, val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FormAsociarTrackViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FormAsociarTrackViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
