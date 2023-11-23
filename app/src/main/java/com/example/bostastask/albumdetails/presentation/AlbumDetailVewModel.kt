package com.example.bostastask.albumdetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bostastask.albumdetails.domain.usecases.PhotosUseCase
import com.example.bostastask.albumdetails.presentation.mappers.toPhotoUiModel
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
class AlbumDetailVewModel @Inject constructor(private val photosUseCase: PhotosUseCase) : ViewModel() {
    private val _detailsState: MutableStateFlow<DetailsState.Display> = MutableStateFlow(DetailsState.Display())
    val detailsState = _detailsState.asStateFlow()

    private val _errorState: MutableSharedFlow<DetailsState.Error> = MutableSharedFlow()
    val errorState = _errorState.asSharedFlow()

    fun getPhotos(albumId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _detailsState.update { it.copy(loading = true) }
            photosUseCase.getPhotos(albumId).let { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let { photos ->
                            _detailsState.update { state ->
                                state.copy(
                                    photos = photos.map { it.toPhotoUiModel() },
                                    filteredPhotos = photos.map { it.toPhotoUiModel() },
                                    loading = false
                                )
                            }
                        }
                    }
                    is Response.Failure -> {
                        response.error?.let { errorMessage ->
                            _errorState.emit(DetailsState.Error(errorMessage))
                        }
                        _detailsState.update { it.copy(loading = false) }
                    }
                }
            }
        }
    }
    fun searchByTitle(key: String) {
        _detailsState.update { state ->
            state.copy(
                filteredPhotos = _detailsState.value.photos.filter { photo ->
                    photo.title.startsWith(key.lowercase(), true)
                }
            )
        }
    }
}