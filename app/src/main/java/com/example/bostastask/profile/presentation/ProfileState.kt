package com.example.bostastask.profile.presentation

import com.example.bostastask.profile.presentation.models.AlbumUiModel
import com.example.bostastask.profile.presentation.models.UserUiModel

sealed interface ProfileState {
    data class Display(
        val user: UserUiModel = UserUiModel(1, "", ""),
        val albums: List<AlbumUiModel> = listOf(),
        val loading: Boolean = false,
    ) : ProfileState

    data class Error(
        val errorMessage: String = ""
    ) : ProfileState
}


