package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Banda
import com.example.vinilos.network.NetworkServiceAdapter

class BandaRepository (val application: Application){
    fun refreshData(callback: (List<Banda>)->Unit, onError: (VolleyError)->Unit) {

        NetworkServiceAdapter.getInstance(application).getBandas({

            callback(it)
        },
            onError
        )
    }
}