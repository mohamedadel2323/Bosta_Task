package com.example.bostastask.profile.domain.repository

import com.example.bostastask.profile.domain.models.AlbumDomainModel
import com.example.bostastask.profile.domain.models.UserDomainModel
import com.example.bostastask.utils.Response

interface IProfileRepository {
    suspend fun getUsers(userId: Int): Response<UserDomainModel>
    suspend fun getAlbums(userId: Int): Response<List<AlbumDomainModel>>
}