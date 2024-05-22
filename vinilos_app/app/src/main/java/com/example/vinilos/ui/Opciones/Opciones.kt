package com.example.vinilos.ui.Opciones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.vinilos.R
import com.example.vinilos.ui.Opciones.OpcionesDirections

class Opciones : Fragment() {

    companion object {
        fun newInstance() = Opciones()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_opciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createAlbumButton: Button = view.findViewById(R.id.opciones_crear_album_btn)
        val bindTrackButton: Button = view.findViewById(R.id.opciones_asociar_track_btn)

        createAlbumButton.setOnClickListener{
            navigateToCreateAlbumForm(view)
        }

        bindTrackButton.setOnClickListener{
            navigateToBindTrackForm(view)
        }
    }

    fun navigateToCreateAlbumForm(view: View) {
        view.findNavController().navigate(OpcionesDirections.actionOpcionesToFormCrearAlbum())
    }

    fun navigateToBindTrackForm(view: View) {
        view.findNavController().navigate(OpcionesDirections.actionOpcionesToFormAsociarTrack())
    }
}