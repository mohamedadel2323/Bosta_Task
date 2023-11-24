package com.example.bostastask.profile.data.remote.users

import com.example.bostastask.data.remote.PlaceholderApiService
import com.example.bostastask.profile.data.dto.user.UserItem
import javax.inject.Inject

class UsersApiClient @Inject constructor(private val placeholderApiService: PlaceholderApiService) :
    UsersRemoteSource {
    override suspend fun getUsers(userId: Int): List<UserItem> = placeholderApiService.getUser(userId)
}