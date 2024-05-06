package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.ItemColeccionistaBinding
import com.example.vinilos.modelos.Coleccionista

class ColeccionistaAdapter: RecyclerView.Adapter<ColeccionistaAdapter.ColeccionistaViewHolder>() {
    var coleccionistas: List<Coleccionista> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColeccionistaViewHolder {
        val withDataBinding: ItemColeccionistaBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ColeccionistaViewHolder.LAYOUT,
            parent,
            false
        )
        return ColeccionistaViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ColeccionistaViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.coleccionista = coleccionistas[position]
        }
    }

    override fun getItemCount(): Int {
        return coleccionistas.size
    }

    class ColeccionistaViewHolder(val viewDataBinding: ItemColeccionistaBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_coleccionista
        }
    }
}