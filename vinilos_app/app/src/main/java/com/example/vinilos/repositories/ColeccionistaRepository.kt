package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Coleccionista
import com.example.vinilos.network.NetworkServiceAdapter

class ColeccionistaRepository (val application: Application){
    fun refreshData(callback: (List<Coleccionista>)->Unit, onError: (VolleyError)->Unit) {

        NetworkServiceAdapter.getInstance(application).getColeccionistas({

            callback(it)
        },
            onError
        )
    }
}