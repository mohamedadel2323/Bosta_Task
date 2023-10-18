package com.example.bostastask.albumdetails.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoUiModel(
    val id: Int,
    val thumbnailUrl: String,
    val url: String,
    val title: String
) : Parcelable
