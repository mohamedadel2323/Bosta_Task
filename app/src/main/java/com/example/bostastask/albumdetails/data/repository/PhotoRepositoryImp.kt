package com.example.bostastask.albumdetails.data.repository

import com.example.bostastask.albumdetails.data.dto.photos.PhotoResponse
import com.example.bostastask.albumdetails.data.mappers.toPhotoDomainResponse
import com.example.bostastask.albumdetails.data.remote.PhotosRemoteSource
import com.example.bostastask.albumdetails.domain.models.PhotoDomainModel
import com.example.bostastask.albumdetails.domain.repository.IPhotoRepository
import com.example.bostastask.utils.Response
import javax.inject.Inject

class PhotoRepositoryImp @Inject constructor(private val photosRemoteSource: PhotosRemoteSource) :
    IPhotoRepository {
    override suspend fun getPhotos(albumId: Int): Response<List<PhotoDomainModel>> =

        when(val response = photosRemoteSource.getPhotos<PhotoResponse>(albumId)){
            is Response.Success -> Response.Success(
                response.data?.toPhotoDomainResponse()?: listOf()
            )
            is Response.Failure-> Response.Failure(response.error?:"Unknown")
            else->{
                Response.Loading()
            }
        }
}