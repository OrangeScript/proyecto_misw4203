package com.example.vinilos.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vinilos.R
import com.example.vinilos.viewmodels.ListaColeccionistaViewModel

class ListaColeccionista : Fragment() {

    companion object {
        fun newInstance() = ListaColeccionista()
    }

    private val viewModel: ListaColeccionistaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_lista_coleccionista, container, false)
    }
}