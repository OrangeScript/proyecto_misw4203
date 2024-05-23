package com.example.vinilos.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentDetalleAlbumBinding
import com.example.vinilos.modelos.Album
import com.example.vinilos.viewmodels.DetalleAlbumViewModel
import com.example.vinilos.ui.DetalleAlbumDirections

class DetalleAlbum : Fragment() {

    companion object {
        fun newInstance() = DetalleAlbum()
    }

    private var _binding: FragmentDetalleAlbumBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetalleAlbumViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {

        }
        val args: DetalleAlbumArgs by navArgs()
        viewModel = ViewModelProvider(this, DetalleAlbumViewModel.Factory(activity.application, args.albumId))
            .get(DetalleAlbumViewModel::class.java)
        viewModel.album.observe(viewLifecycleOwner, Observer<Album> {
            it.apply {
                binding.album = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goBackButton: Button = view.findViewById(R.id.detalle_album_to_lista_album_button)
//        val asociarTrackButton: Button = view.findViewById(R.id.album_asociar_track_button)

        goBackButton.setOnClickListener{
            goFromAlbumDetailToList(view)
        }

//        asociarTrackButton.setOnClickListener{
//            goFromAlbumToAsociarTrack(view)
//        }
    }

    fun goFromAlbumDetailToList(view: View) {
        view.findNavController().popBackStack()
    }

    fun goFromAlbumToAsociarTrack(view: View) {
        view.findNavController().navigate(DetalleAlbumDirections.actionDetalleAlbumToFormAsociarTrack(binding.album!!.id))
    }
}