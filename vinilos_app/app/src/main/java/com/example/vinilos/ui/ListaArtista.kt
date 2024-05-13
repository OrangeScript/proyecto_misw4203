package com.example.vinilos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.databinding.FragmentListaArtistaBinding
import com.example.vinilos.modelos.Artista
import com.example.vinilos.ui.adapters.ArtistaAdapter
import com.example.vinilos.viewmodels.ListaArtistaViewModel

class ListaArtista : Fragment() {

    companion object {
        fun newInstance() = ListaArtista()
    }

    private var _binding: FragmentListaArtistaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListaArtistaViewModel
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: ArtistaAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaArtistaBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = ArtistaAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerViewArtistas
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, ListaArtistaViewModel.Factory(activity.application))
            .get(ListaArtistaViewModel::class.java)
        viewModel.artistas.observe(viewLifecycleOwner, Observer<List<Artista>> {
            it.apply {
                viewModelAdapter!!.artistas = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(context, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}