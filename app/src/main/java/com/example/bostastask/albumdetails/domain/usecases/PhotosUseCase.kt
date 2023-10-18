package com.example.bostastask.albumdetails.domain.usecases

import com.example.bostastask.albumdetails.domain.models.PhotoDomainModel
import com.example.bostastask.albumdetails.domain.repository.IPhotoRepository
import com.example.bostastask.utils.Response
import javax.inject.Inject

class PhotosUseCase @Inject constructor(private val repository: IPhotoRepository) {
    suspend fun getPhotos(albumId: Int): Response<List<PhotoDomainModel>> =
        repository.getPhotos(albumId)
}