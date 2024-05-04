package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.modelos.Musico
import com.example.vinilos.repositories.DetalleMusicoRepository

class DetalleMusicoViewModel(application: Application, musicoId: Int) : AndroidViewModel(application) {

    private val DetalleMusicoRepository = DetalleMusicoRepository(application)

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
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        DetalleMusicoRepository.refreshData(
            musicoId,
            {
                _musico.postValue(it)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            },
            {
                _eventNetworkError.value = true
            }
        )
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