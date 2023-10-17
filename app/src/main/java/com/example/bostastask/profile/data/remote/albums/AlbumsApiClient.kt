package com.example.bostastask.profile.data.remote.albums

import com.example.bostastask.data.remote.PlaceholderApiService
import com.example.bostastask.utils.Response
import javax.inject.Inject

class AlbumsApiClient @Inject constructor(private val placeholderApiService: PlaceholderApiService) :
    AlbumsRemoteSource {
    override suspend fun <T> getAlbums(userId: Int): Response<T> =
        try {
            Response.Success(placeholderApiService.getAlbums(userId) as T)
        } catch (e: Exception) {
            Response.Failure(e.message ?: "Unknown")
        }
}