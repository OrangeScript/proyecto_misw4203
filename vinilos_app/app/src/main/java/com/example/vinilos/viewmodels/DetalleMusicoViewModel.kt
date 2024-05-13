package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.vinilos.modelos.Musico
import com.example.vinilos.repositories.ArtistaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalleMusicoViewModel(application: Application, musicoId: Int) : AndroidViewModel(application) {

    private val artistaRepository = ArtistaRepository(application)

    private val _musico = MutableLiveData<Musico>()

    val musicoId : Int = musicoId

    val musico: LiveData<Musico>
        get() = _musico

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshdataFromNetwork()
    }

    private fun refreshdataFromNetwork() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                withContext(Dispatchers.IO) {
                    val data = artistaRepository.refreshDataMusico(musicoId)
                    _musico.postValue(data as Musico?)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            } catch (e: Exception) {
                _eventNetworkError.postValue(true)
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val musicoId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetalleMusicoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetalleMusicoViewModel(app, musicoId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}