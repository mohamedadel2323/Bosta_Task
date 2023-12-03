package com.example.bostastask.albumdetails.presentation

import com.example.bostastask.albumdetails.presentation.models.PhotoUiModel


sealed interface DetailsState {
    data class Display(
        val photos: List<PhotoUiModel> = listOf(),
        val filteredPhotos: List<PhotoUiModel> = listOf(),
        val loading: Boolean = false,
        val noSearchMatch: Boolean = false
    ) : DetailsState

    data class Error(
        val errorMessage: String = ""
    ) : DetailsState
}


