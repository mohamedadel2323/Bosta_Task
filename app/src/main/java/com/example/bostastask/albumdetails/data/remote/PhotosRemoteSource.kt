package com.example.bostastask.albumdetails.data.remote

import com.example.bostastask.albumdetails.data.dto.photos.PhotoItem


interface PhotosRemoteSource {
    suspend fun getPhotos(albumId: Int): List<PhotoItem>
}