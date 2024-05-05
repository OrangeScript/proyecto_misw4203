package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.modelos.Banda
import com.example.vinilos.repositories.ArtistaRepository

class ListaArtistaViewModel(application: Application) : AndroidViewModel(application) {

    private val artistaRepository = ArtistaRepository(application)

    private val _bandas = MutableLiveData<List<Banda>>()

    val bandas: LiveData<List<Banda>>
        get() = _bandas

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

//    init {
//        refreshDataFromNetwork()
//    }
//
//    fun refreshDataFromNetwork() {
//        artistaRepository.refreshData({
//            _bandas.postValue(it)
//            _eventNetworkError.value = false
//            _isNetworkErrorShown.value = false
//        },{
//            _eventNetworkError.value = true
//        })
//    }

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