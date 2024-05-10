package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.ItemArtistaBinding
import com.example.vinilos.modelos.Artista
import com.example.vinilos.modelos.Banda
import com.example.vinilos.modelos.Musico
import com.example.vinilos.ui.ListaArtistaDirections

class ArtistaAdapter: RecyclerView.Adapter<ArtistaAdapter.ArtistaViewHolder>() {

    var artistas: List<Artista> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistaViewHolder {
        val withDataBinding: ItemArtistaBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistaViewHolder.LAYOUT,
            parent,
            false
        )
        return ArtistaViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistaViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artista = artistas[position]
        }
        holder.viewDataBinding.root.setOnClickListener{
            when (val artista = artistas[position]) {
                is Banda -> {
                    val action = ListaArtistaDirections.actionListaArtistaToDetalleArtista(artista.id)
                    holder.viewDataBinding.root.findNavController().navigate(action)
                }
                is Musico -> {
                    val action = ListaArtistaDirections.actionListaArtistaToDetalleMusico(artista.id)
                    holder.viewDataBinding.root.findNavController().navigate(action)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return artistas.size
    }

    class ArtistaViewHolder(val viewDataBinding: ItemArtistaBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_artista
        }
    }
}