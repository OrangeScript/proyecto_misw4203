package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.modelos.Banda
import com.example.vinilos.repositories.DetalleBandaRepository

class DetalleBandaViewModel(application: Application, bandaId: Int) : AndroidViewModel(application) {

    private val DetalleBandaRepository = DetalleBandaRepository(application)

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
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        DetalleBandaRepository.refreshData(
            bandaId,
            {
                _banda.postValue(it)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            },
            {
                _eventNetworkError.value = true
            }
        )
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
    class Factory(val app: Application, val bandaId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetalleBandaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetalleBandaViewModel(app, bandaId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}