package com.example.vinilos.repositories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Banda
import com.example.vinilos.network.NetworkServiceAdapter

class DetalleBandaRepository (application: Application): AndroidViewModel(application) {
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    fun refreshData(id: Int, onComplete: (resp: Banda) -> Unit, onError: (VolleyError)->Unit) {
        networkServiceAdapter.getBanda(id, onComplete, onError)
    }
}