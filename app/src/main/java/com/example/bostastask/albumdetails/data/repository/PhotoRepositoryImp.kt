package com.example.bostastask.albumdetails.data.repository

import com.example.bostastask.albumdetails.data.mappers.toPhotoDomainResponse
import com.example.bostastask.albumdetails.data.remote.PhotosRemoteSource
import com.example.bostastask.albumdetails.domain.models.PhotoDomainModel
import com.example.bostastask.albumdetails.domain.repository.IPhotoRepository
import com.example.bostastask.utils.Response
import javax.inject.Inject

class PhotoRepositoryImp @Inject constructor(private val photosRemoteSource: PhotosRemoteSource) : IPhotoRepository {
    override suspend fun getPhotos(albumId: Int): Response<List<PhotoDomainModel>> =
        try {
            Response.Success(photosRemoteSource.getPhotos(albumId).toPhotoDomainResponse())
        } catch (e: Exception) {
            Response.Failure(e.message ?: "Unknown Error")
        }
}