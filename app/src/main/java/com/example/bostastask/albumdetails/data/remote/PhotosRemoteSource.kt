package com.example.bostastask.albumdetails.data.remote

import com.example.bostastask.albumdetails.data.dto.photos.PhotosResponse


interface PhotosRemoteSource {
    suspend fun getPhotos(albumId: Int): PhotosResponse
}