package com.example.bostastask.albumdetails.data.remote


import com.example.bostastask.albumdetails.data.dto.photos.PhotoItem
import com.example.bostastask.data.remote.PlaceholderApiService
import javax.inject.Inject

class PhotoApiClient @Inject constructor(private val placeholderApiService: PlaceholderApiService) :
    PhotosRemoteSource {
    override suspend fun getPhotos(albumId: Int): List<PhotoItem> = placeholderApiService.getPhotos(albumId)
}