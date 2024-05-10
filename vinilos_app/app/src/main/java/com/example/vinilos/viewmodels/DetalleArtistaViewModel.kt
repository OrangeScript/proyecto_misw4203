package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.vinilos.modelos.Banda
import com.example.vinilos.repositories.ArtistaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalleArtistaViewModel(application: Application, bandaId: Int) : AndroidViewModel(application) {

    private val artistaRepository = ArtistaRepository(application)

    private val _banda = MutableLiveData<Banda>()

    val bandaId : Int = bandaId

    val banda: LiveData<Banda>
        get() = _banda

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshdataFromNetwork()
    }

    private fun refreshdataFromNetwork () {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                withContext(Dispatchers.IO) {
                    val data = artistaRepository.refreshDataBanda(bandaId)
                    _banda.postValue(data)
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
    class Factory(val app: Application, val bandaId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetalleArtistaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetalleArtistaViewModel(app, bandaId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}