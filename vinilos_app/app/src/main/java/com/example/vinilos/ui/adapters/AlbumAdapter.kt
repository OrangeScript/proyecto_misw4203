package com.example.vinilos.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.ItemAlbumBinding
import com.example.vinilos.modelos.Album
import com.example.vinilos.ui.ListaAlbum
import com.example.vinilos.viewmodels.ListaAlbumViewModel


class AlbumAdapter: RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

        /*inner class AlbumViewHolder(var view: ItemAlbumBinding)
            : RecyclerView.ViewHolder(view.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
            val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AlbumViewHolder(binding)
        }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumList.get(position)
        val view = holder.view

        view.AlbumCardView.setOnClickListener {
            //val action = ListaAlbumDirections.actionListaAlbumToDetalleAlbum()
            //view.root.findNavController().navigate(action)
        }
        view.albumName.text = album.name
        view.albumReleaseDate.text = album.releaseDate
    }*/


    /*fun updateAlbums(newAlbums: List<Album>) {
        albums = newAlbums
        notifyDataSetChanged()
    }*/
    /*class AlbumViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.album = album
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemAlbumBinding.inflate(layoutInflater, parent, false)
        return AlbumViewHolder(binding)
    }*/

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(itemView)
    }*/

    /*override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.bind(album)
    }

    override fun getItemCount(): Int {
        return albums.size
    }*/

    class AlbumViewHolder(val viewDataBinding: ItemAlbumBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_album
        }
    }

    var albums: List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: ItemAlbumBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false
        )
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
      /*  holder.viewDataBinding.root.setOnClickListener {
//            val action = ListaAlbumDirections.actionListaAlbuFragmentToAlbumFragment()
            holder.viewDataBinding.root.findNavController().navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    /*class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val albumCover = itemView.findViewById<ImageView>(R.id.albumCover)
        private val albumName = itemView.findViewById<TextView>(R.id.albumName)

        fun bind(album: Album) {
            albumName.text = album.name
        }

    }*/
}