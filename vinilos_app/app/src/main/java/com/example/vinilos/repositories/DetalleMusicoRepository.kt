package com.example.vinilos.repositories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Musico
import com.example.vinilos.network.NetworkServiceAdapter

class DetalleMusicoRepository(application: Application): AndroidViewModel(application) {
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    fun refreshData(id: Int, onComplete: (resp: Musico) -> Unit, onError: (VolleyError)->Unit) {
        networkServiceAdapter.getMusico(id, onComplete, onError)
    }
}