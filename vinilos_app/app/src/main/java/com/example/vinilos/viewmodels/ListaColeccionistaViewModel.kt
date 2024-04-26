package com.example.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vinilos.modelos.Coleccionista
import com.example.vinilos.repositories.ColeccionistaRepository

class ListaColeccionistaViewModel(application: Application) :  AndroidViewModel(application) {

    private val collectorsRepository = ColeccionistaRepository(application)

    private val _collectors = MutableLiveData<List<Coleccionista>>()

    val collectors: LiveData<List<Coleccionista>>
        get() = _collectors

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
        collectorsRepository.refreshData({
            _collectors.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            Log.d("Error", it.toString())
            _eventNetworkError.value = true
        })
    }
}