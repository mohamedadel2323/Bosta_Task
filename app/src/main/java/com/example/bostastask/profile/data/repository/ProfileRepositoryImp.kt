package com.example.bostastask.profile.data.repository

import com.example.bostastask.profile.data.dto.album.AlbumResponse
import com.example.bostastask.profile.data.dto.user.UserResponse
import com.example.bostastask.profile.data.mappers.toAlbumDomainResponse
import com.example.bostastask.profile.data.mappers.toUserDomainModel
import com.example.bostastask.profile.data.remote.albums.AlbumsRemoteSource
import com.example.bostastask.profile.data.remote.users.UsersRemoteSource
import com.example.bostastask.profile.domain.models.AddressDomainModel
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
        when (val response = usersRemoteSource.getUsers<UserResponse>(userId)) {
            is Response.Success -> Response.Success(
                response.data?.first()?.toUserDomainModel()
                    ?: UserDomainModel(1, "", AddressDomainModel("", "", "", ""))
            )

            is Response.Failure -> Response.Failure(response.error ?: "Unknown")

            else -> Response.Loading()
        }


    override suspend fun getAlbums(userId: Int): Response<List<AlbumDomainModel>> =
        when (val response = albumsRemoteSource.getAlbums<AlbumResponse>(userId)) {
            is Response.Success -> Response.Success(
                response.data?.toAlbumDomainResponse()
                    ?: listOf()
            )

            is Response.Failure -> Response.Failure(response.error ?: "Unknown")
            else -> {
                Response.Loading()
            }
        }
}