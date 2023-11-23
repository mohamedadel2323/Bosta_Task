package com.example.bostastask.profile.data.remote.users

import com.example.bostastask.profile.data.dto.user.UserResponse

interface UsersRemoteSource {
    suspend fun getUsers(userId: Int): UserResponse
}