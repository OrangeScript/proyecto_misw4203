package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Musico
import com.example.vinilos.network.NetworkServiceAdapter

class MusicoRepository (val application: Application){

    fun refreshData(callback: (List<Musico>)->Unit, onError: (VolleyError)->Unit) {

        NetworkServiceAdapter.getInstance(application).getMusicos({

            callback(it)
        },
            onError
        )
    }


}