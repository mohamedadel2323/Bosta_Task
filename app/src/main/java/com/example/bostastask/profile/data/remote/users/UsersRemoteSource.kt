package com.example.bostastask.profile.data.remote.users

import com.example.bostastask.utils.Response

interface UsersRemoteSource {
    suspend fun<T> getUsers(userId: Int): Response<T>
}