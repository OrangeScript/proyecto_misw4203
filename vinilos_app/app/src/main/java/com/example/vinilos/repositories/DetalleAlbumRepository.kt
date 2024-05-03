package com.example.vinilos.repositories

import android.app.Application
import android.app.SharedElementCallback
import androidx.lifecycle.AndroidViewModel
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Album
import com.example.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class DetalleAlbumRepository (application: Application): AndroidViewModel(application) {
    private val networkServiceAdapter = NetworkServiceAdapter.getInstance(application)
    fun refreshData(id: Int, onComplete: (resp: Album) -> Unit, onError: (VolleyError)->Unit) {
        networkServiceAdapter.getAlbum(id, onComplete, onError)
    }
}


//fun getAlbum(albumId: Int, onComplete:(resp:Album)->Unit, onError: (error:VolleyError)->Unit){
//    requestQueue.add(getRequest("albums/$albumId",
//        { response ->
//            val resp = JSONObject(response)
//            var objectResp = Album(id = resp.getInt("id"),
//                name = resp.getString("name"),
//                cover = resp.getString("cover"),
//                releaseDate = resp.getString("releaseDate"),
//                genre = resp.getString("genre"),
//                description = resp.getString("description"),
//                recordLabel = resp.getString("recordLabel"))
//            onComplete(objectResp)
//        },
//        {
//            onError(it)
//        }))
//}