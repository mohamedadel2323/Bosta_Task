package com.example.bostastask.albumdetails.data.remote

import com.example.bostastask.utils.Response


interface PhotosRemoteSource {
    suspend fun <T> getPhotos(albumId: Int): Response<T>
}