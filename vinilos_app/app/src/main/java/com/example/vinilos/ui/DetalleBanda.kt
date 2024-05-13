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
import com.example.vinilos.databinding.FragmentDetalleArtistaBinding
import com.example.vinilos.modelos.Banda
import com.example.vinilos.viewmodels.DetalleBandaViewModel

class DetalleBanda : Fragment() {

    companion object {
        fun newInstance() = DetalleBanda()
    }

    private var _binding: FragmentDetalleArtistaBinding? = null

    private val binding get() = _binding!!
    private lateinit var viewModel: DetalleBandaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleArtistaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {

        }

        val args: DetalleBandaArgs by navArgs()
        viewModel = ViewModelProvider(
            this,
            DetalleBandaViewModel.Factory(activity.application, args.bandaId)
        )
            .get(DetalleBandaViewModel::class.java)
        viewModel.banda.observe(viewLifecycleOwner, Observer<Banda> {
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

        val goBackButton: Button = view.findViewById(R.id.detalle_banda_to_lista_banda_button)

        goBackButton.setOnClickListener{
            goFromArtistaDetailToList(view)
        }
    }

    fun goFromArtistaDetailToList(view: View) {
        view.findNavController().popBackStack()
    }


}