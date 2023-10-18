package com.example.bostastask.albumdetails.domain.repository

import com.example.bostastask.albumdetails.domain.models.PhotoDomainModel
import com.example.bostastask.utils.Response

interface IPhotoRepository {
    suspend fun getPhotos(albumId: Int):Response<List<PhotoDomainModel>>
}