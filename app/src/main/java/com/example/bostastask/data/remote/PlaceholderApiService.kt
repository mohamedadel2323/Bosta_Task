package com.example.bostastask.data.remote

import com.example.bostastask.albumdetails.data.dto.photos.PhotoResponse
import com.example.bostastask.profile.data.dto.album.AlbumResponse
import com.example.bostastask.profile.data.dto.user.UserResponse
import retrofit2.http.GET

interface PlaceholderApiService {
    companion object {
        const val USERS = "users"
        const val ALBUMS = "albums"
        const val PHOTOS = "photos"
    }

    @GET(USERS)
    suspend fun getUsers(): UserResponse

    @GET(ALBUMS)
    suspend fun getAlbums(): AlbumResponse

    @GET(PHOTOS)
    suspend fun getPhotos(): PhotoResponse
}