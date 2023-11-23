package com.example.bostastask.profile.data.remote.albums

import com.example.bostastask.profile.data.dto.album.AlbumResponse

interface AlbumsRemoteSource {
    suspend fun getAlbums(userId: Int): AlbumResponse
}