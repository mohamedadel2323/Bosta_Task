package com.example.bostastask.data.remote

import com.example.bostastask.albumdetails.data.dto.photos.PhotoResponse
import com.example.bostastask.profile.data.dto.album.AlbumResponse
import com.example.bostastask.profile.data.dto.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceholderApiService {
    companion object {
        const val USERS = "users"
        const val ID = "id"
        const val USER_ID = "userId"
        const val ALBUMS = "albums"
        const val PHOTOS = "photos"
    }

    @GET(USERS)
    suspend fun getUser(@Query(ID) userId: Int): UserResponse

    @GET(ALBUMS)
    suspend fun getAlbums(@Query(USER_ID) userId: Int): AlbumResponse

    @GET(PHOTOS)
    suspend fun getPhotos(): PhotoResponse
}