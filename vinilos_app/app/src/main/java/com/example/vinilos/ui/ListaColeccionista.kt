package com.example.vinilos.ui

import androidx.fragment.app.viewModels
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
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentListaColeccionistaBinding
import com.example.vinilos.modelos.Coleccionista
import com.example.vinilos.ui.adapters.ColeccionistaAdapter
import com.example.vinilos.viewmodels.ListaColeccionistaViewModel

class ListaColeccionista : Fragment() {

    companion object {
        fun newInstance() = ListaColeccionista()
    }

    private var _binding: FragmentListaColeccionistaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListaColeccionistaViewModel
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: ColeccionistaAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaColeccionistaBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = ColeccionistaAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerViewColeccionista
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
        viewModel = ViewModelProvider(this, ListaColeccionistaViewModel.Factory(activity.application))
            .get(ListaColeccionistaViewModel::class.java)
        viewModel.collectors.observe(viewLifecycleOwner, Observer<List<Coleccionista>> {
            it.apply {
                viewModelAdapter!!.coleccionistas = this
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