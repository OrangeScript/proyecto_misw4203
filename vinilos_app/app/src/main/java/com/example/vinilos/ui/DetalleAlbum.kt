package com.example.vinilos.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.vinilos.R
import com.example.vinilos.viewmodels.DetalleAlbumViewModel
import com.example.vinilos.databinding.FragmentDetalleAlbumBinding
import com.example.vinilos.modelos.Album

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
}