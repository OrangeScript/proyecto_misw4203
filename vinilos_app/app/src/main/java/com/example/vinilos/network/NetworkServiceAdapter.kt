package com.example.vinilos.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.modelos.Album
import com.example.vinilos.modelos.Coleccionista
import com.example.vinilos.modelos.Artista
import com.example.vinilos.modelos.Musico
import com.example.vinilos.modelos.Banda
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {

    companion object{
        const val BASE_URL= "http://35.202.221.176:3000/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL +path, responseListener,errorListener)
    }

    private fun putRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL +path, body, responseListener, errorListener)
    }

    suspend fun getAlbums() = suspendCoroutine<List<Album>> { cont ->
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val album = Album(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        cover = item.getString("cover"),
                        releaseDate = item.getString("releaseDate"),
                        genre = item.getString("genre"),
                        description = item.getString("description"),
                        recordLabel = item.getString("recordLabel")
                    )
                    list.add(i, album)
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getAlbum(albumId: Int) = suspendCoroutine { cont ->
        requestQueue.add(getRequest("albums/$albumId",
            Response.Listener { response ->
                val resp = JSONObject(response)
                val album = Album(
                    id = resp.getInt("id"),
                    name = resp.getString("name"),
                    cover = resp.getString("cover"),
                    releaseDate = resp.getString("releaseDate"),
                    genre = resp.getString("genre"),
                    description = resp.getString("description"),
                    recordLabel = resp.getString("recordLabel")
                )
                cont.resume(album)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getBandas() = suspendCoroutine<List<Artista>> { cont ->
        requestQueue.add(getRequest("bands",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Artista>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val banda = Banda(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        image = item.getString("image"),
                        description = item.getString("description"),
                        creationdate = item.getString("creationDate")
                    )
                    list.add(i, banda)
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getBanda(bandaId: Int) = suspendCoroutine { cont ->
        requestQueue.add(getRequest("bands/$bandaId",
            Response.Listener { response ->
                val resp = JSONObject(response)
                val banda = Banda(
                    id = resp.getInt("id"),
                    name = resp.getString("name"),
                    image = resp.getString("image"),
                    description = resp.getString("description"),
                    creationdate = resp.getString("creationDate")
                )
                cont.resume(banda)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getMusicos() = suspendCoroutine<List<Artista>> { cont ->
        requestQueue.add(getRequest("musicians",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Artista>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val musico = Musico(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        image = item.getString("image"),
                        description = item.getString("description"),
                        birthdate = item.getString("birthDate")
                    )
                    list.add(i, musico)
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getMusico(musicoId: Int) = suspendCoroutine { cont ->
        requestQueue.add(getRequest("musicians/$musicoId",
            Response.Listener { response ->
                val resp = JSONObject(response)
                val musico = Musico(
                    id = resp.getInt("id"),
                    name = resp.getString("name"),
                    image = resp.getString("image"),
                    description = resp.getString("description"),
                    birthdate = resp.getString("birthDate")
                )
                cont.resume(musico)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getColeccionistas() = suspendCoroutine<List<Coleccionista>> { cont ->
        requestQueue.add(getRequest("collectors",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Coleccionista>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val coleccionista = Coleccionista(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        telephone = item.getString("telephone"),
                        email = item.getString("email")
                    )
                    list.add(i, coleccionista)
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL +path, body, responseListener, errorListener)
    }

    suspend fun postAsociarTrack(
        albumId: Int,
        body: JSONObject) = suspendCoroutine { cont ->
        requestQueue.add(postRequest(
            "albums/$albumId/tracks",
            body,
            Response.Listener { response ->
                cont.resume(response)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }
            ))

    }

    suspend fun postCrearAlbum(
        body: JSONObject) = suspendCoroutine { cont ->
            requestQueue.add(postRequest(
                "albums",
                body,
                Response.Listener { response ->
                    cont.resume(response)
                },
                Response.ErrorListener {
                    cont.resumeWithException(it)
                }
            ))
    }

}