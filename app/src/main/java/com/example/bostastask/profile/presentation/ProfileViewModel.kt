package com.example.bostastask.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bostastask.profile.domain.usecases.ProfileUseCase
import com.example.bostastask.profile.presentation.mappers.toAlbumUiModel
import com.example.bostastask.profile.presentation.mappers.toUserUiModel
import com.example.bostastask.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: ProfileUseCase) : ViewModel() {
    init { fetchUserData() }

    private val _profileState: MutableStateFlow<ProfileState.Display> = MutableStateFlow(ProfileState.Display())
    val profileState = _profileState.asStateFlow()

    private val _errorState: MutableSharedFlow<ProfileState.Error> = MutableSharedFlow()
    val errorState = _errorState.asSharedFlow()

    private fun fetchUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _profileState.update { it.copy(loading = true) }
            profileUseCase.getRandomUser().let { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let {
                            _profileState.update { it.copy(user = response.data.toUserUiModel(), loading = false) }
                            fetchAlbumsData(response.data.userId)
                        }
                    }
                    is Response.Failure -> {
                        response.error?.let { errorMessage ->
                            _errorState.emit(ProfileState.Error(errorMessage))
                        }
                        _profileState.update { it.copy(loading = false) }
                    }
                }
            }
        }
    }
    private fun fetchAlbumsData(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _profileState.update { it.copy(loading = true) }
            profileUseCase.getAlbums(userId).let { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let {
                            _profileState.update { state ->
                                state.copy(albums = response.data.map { it.toAlbumUiModel() }, loading = false)
                            }
                        }
                    }
                    is Response.Failure -> {
                        response.error?.let { errorMessage ->
                            _errorState.emit(ProfileState.Error(errorMessage))
                        }
                        _profileState.update { it.copy(loading = false) }
                    }
                }
            }
        }
    }
}