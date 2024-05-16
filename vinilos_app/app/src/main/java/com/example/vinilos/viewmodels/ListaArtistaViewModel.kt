package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.android.volley.TimeoutError
import com.example.vinilos.modelos.Artista
import com.example.vinilos.repositories.ArtistaRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaArtistaViewModel(application: Application) : AndroidViewModel(application) {

    private val artistaRepository = ArtistaRepository(application)

    private val _artistas = MutableLiveData<List<Artista>>()

    val artistas: LiveData<List<Artista>>
        get() = _artistas

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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.IO) {
                    val bandas = async { artistaRepository.refreshDataBandas() }
                    val musicos = async { artistaRepository.refreshDataMusicos() }

                    val artistasCombinados = bandas.await()!! + musicos.await()!!
                    _artistas.postValue(artistasCombinados)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            } catch (e: TimeoutError) {
                _eventNetworkError.postValue(true)
            } catch (e: Exception) {
                _eventNetworkError.postValue(true)
            }
        }

    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListaArtistaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ListaArtistaViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}