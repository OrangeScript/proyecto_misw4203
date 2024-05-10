package com.example.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentDetalleMusicoBinding
import com.example.vinilos.modelos.Musico
import com.example.vinilos.viewmodels.DetalleMusicoViewModel

class DetalleMusico  : Fragment() {

    companion object {
        fun newInstance() = DetalleMusico()
    }

    private var _binding: FragmentDetalleMusicoBinding? = null

    private val binding get() = _binding!!
    private lateinit var viewModel: DetalleMusicoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleMusicoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {

        }

        val args: DetalleMusicoArgs by navArgs()
        viewModel = ViewModelProvider(
            this,
            DetalleMusicoViewModel.Factory(activity.application, args.musicoId)
        )
            .get(DetalleMusicoViewModel::class.java)
        viewModel.musico.observe(viewLifecycleOwner, Observer<Musico> {
            it.apply {
                binding.artista = this
            }
        })
        viewModel.eventNetworkError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isNetworkError ->
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

        val goBackButton: Button = view.findViewById(R.id.detalle_musico_to_lista_banda_button)

        goBackButton.setOnClickListener{
            goFromArtistaDetailToList(view)
        }
    }

    private fun goFromArtistaDetailToList(view: View) {
        view.findNavController().popBackStack()
    }
}