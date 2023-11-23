package com.example.bostastask.profile.data.repository

import com.example.bostastask.profile.data.mappers.toAlbumDomainModel
import com.example.bostastask.profile.data.mappers.toUserDomainModel
import com.example.bostastask.profile.data.remote.albums.AlbumsRemoteSource
import com.example.bostastask.profile.data.remote.users.UsersRemoteSource
import com.example.bostastask.profile.domain.models.AlbumDomainModel
import com.example.bostastask.profile.domain.models.UserDomainModel
import com.example.bostastask.profile.domain.repository.IProfileRepository
import com.example.bostastask.utils.Response
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor(
    private val usersRemoteSource: UsersRemoteSource,
    private val albumsRemoteSource: AlbumsRemoteSource
) : IProfileRepository {

    override suspend fun getUsers(userId: Int): Response<UserDomainModel> =
        try {
            Response.Success(usersRemoteSource.getUsers(userId).first().toUserDomainModel())
        }catch (e: Exception){
            Response.Failure(e.message?:"Unknown Error")
        }

    override suspend fun getAlbums(userId: Int): Response<List<AlbumDomainModel>> =
        try {
            Response.Success(albumsRemoteSource.getAlbums(userId).map { it.toAlbumDomainModel() })
        }catch (e: Exception){
            Response.Failure(e.message?:"Unknown Error")
        }
}