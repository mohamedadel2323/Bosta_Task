package com.example.bostastask.albumdetails.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bostastask.R
import com.example.bostastask.albumdetails.presentation.models.PhotoUiModel
import com.example.bostastask.databinding.PhotoListItemBinding


class PhotosAdapter(private val selectPhoto: (PhotoUiModel) -> Unit) :
    ListAdapter<PhotoUiModel, PhotoViewHolder>(PhotoDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = DataBindingUtil.inflate<PhotoListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.photo_list_item,
            parent,
            false
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
        holder.binding.root.setOnClickListener {
            selectPhoto(photo)
        }
    }
}

class PhotoViewHolder(val binding: PhotoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: PhotoUiModel) {
        binding.photo = photo
        binding.executePendingBindings()
    }
}

class PhotoDiffUtil : DiffUtil.ItemCallback<PhotoUiModel>() {
    override fun areItemsTheSame(oldItem: PhotoUiModel, newItem: PhotoUiModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: PhotoUiModel, newItem: PhotoUiModel): Boolean {
        return oldItem == newItem
    }

}
