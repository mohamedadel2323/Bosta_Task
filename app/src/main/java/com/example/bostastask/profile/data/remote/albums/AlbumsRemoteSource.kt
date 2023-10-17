package com.example.bostastask.profile.data.remote.albums

import com.example.bostastask.utils.Response

interface AlbumsRemoteSource {
    suspend fun <T> getAlbums(userId: Int): Response<T>
}