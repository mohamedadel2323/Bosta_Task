package com.example.bostastask.albumdetails.data.remote


import com.example.bostastask.data.remote.PlaceholderApiService
import com.example.bostastask.utils.Response
import timber.log.Timber
import javax.inject.Inject

class PhotoApiClient @Inject constructor(private val placeholderApiService: PlaceholderApiService) :
    PhotosRemoteSource {
    override suspend fun <T> getPhotos(albumId: Int): Response<T> =
        try {
            Response.Success(placeholderApiService.getPhotos(albumId) as T)
        } catch (e: Exception) {
            Response.Failure(e.message ?: "Unknown")
        }

}