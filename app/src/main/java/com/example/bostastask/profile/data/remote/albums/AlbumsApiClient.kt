package com.example.bostastask.profile.data.remote.albums

import com.example.bostastask.data.remote.PlaceholderApiService
import com.example.bostastask.profile.data.dto.album.AlbumItem
import javax.inject.Inject

class AlbumsApiClient @Inject constructor(private val placeholderApiService: PlaceholderApiService) :
    AlbumsRemoteSource {
    override suspend fun  getAlbums(userId: Int): List<AlbumItem> = placeholderApiService.getAlbums(userId)
}