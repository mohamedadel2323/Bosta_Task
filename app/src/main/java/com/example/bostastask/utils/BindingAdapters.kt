package com.example.bostastask.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.bostastask.R

@BindingAdapter("url")
fun downloadPhoto(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(it)
            .placeholder(R.drawable.loading_img)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }

}