package com.example.bostastask.profile.data.remote.users

import com.example.bostastask.data.remote.PlaceholderApiService
import com.example.bostastask.utils.Response
import javax.inject.Inject

class UsersApiClient @Inject constructor(private val placeholderApiService: PlaceholderApiService) :
    UsersRemoteSource {
    override suspend fun <T> getUsers(userId: Int): Response<T> =
        try {
            Response.Success(placeholderApiService.getUser(userId) as T)
        } catch (e: Exception) {
            Response.Failure(e.message ?: "Unknown")
        }
}