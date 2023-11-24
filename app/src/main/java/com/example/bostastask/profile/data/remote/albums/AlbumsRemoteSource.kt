package com.example.bostastask.profile.data.remote.albums

import com.example.bostastask.profile.data.dto.album.AlbumItem

interface AlbumsRemoteSource {
    suspend fun getAlbums(userId: Int): List<AlbumItem>
}