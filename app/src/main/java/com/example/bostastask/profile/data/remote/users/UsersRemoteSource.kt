package com.example.bostastask.profile.data.remote.users

import com.example.bostastask.profile.data.dto.user.UserItem

interface UsersRemoteSource {
    suspend fun getUsers(userId: Int): List<UserItem>
}