package com.example.bostastask.profile.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumUiModel(
    val id: Int,
    val title: String
) : Parcelable
