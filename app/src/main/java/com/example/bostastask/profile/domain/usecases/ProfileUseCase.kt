package com.example.bostastask.profile.domain.usecases

import com.example.bostastask.profile.domain.models.AlbumDomainModel
import com.example.bostastask.profile.domain.models.UserDomainModel
import com.example.bostastask.profile.domain.repository.IProfileRepository
import com.example.bostastask.utils.Response
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val iProfileRepository: IProfileRepository) {
    suspend fun getRandomUser(): Response<UserDomainModel> =
        iProfileRepository.getUsers((1..10).random())

    suspend fun getAlbums(userId: Int): Response<List<AlbumDomainModel>> =
        iProfileRepository.getAlbums(userId)

}