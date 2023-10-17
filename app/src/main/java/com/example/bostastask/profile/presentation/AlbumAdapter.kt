package com.example.bostastask.profile.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bostastask.R
import com.example.bostastask.databinding.AlbumListItemBinding
import com.example.bostastask.profile.presentation.models.AlbumUiModel

class AlbumAdapter(private val selectAlbum: (AlbumUiModel) -> Unit) :
    ListAdapter<AlbumUiModel, AlbumViewHolder>(AlbumDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = DataBindingUtil.inflate<AlbumListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.album_list_item, parent, false
        )
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album)
        holder.binding.root.setOnClickListener {
            selectAlbum(album)
        }
    }
}

class AlbumViewHolder(val binding: AlbumListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(album: AlbumUiModel) {
        binding.album = album
    }
}

class AlbumDiffUtil : DiffUtil.ItemCallback<AlbumUiModel>() {
    override fun areItemsTheSame(oldItem: AlbumUiModel, newItem: AlbumUiModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: AlbumUiModel, newItem: AlbumUiModel): Boolean {
        return oldItem == newItem
    }

}