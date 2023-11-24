package com.example.bostastask.data.remote

import com.example.bostastask.albumdetails.data.dto.photos.PhotoItem
import com.example.bostastask.profile.data.dto.album.AlbumItem
import com.example.bostastask.profile.data.dto.user.UserItem
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceholderApiService {
    companion object {
        const val USERS = "users"
        const val ID = "id"
        const val USER_ID = "userId"
        const val ALBUM_ID = "albumId"
        const val ALBUMS = "albums"
        const val PHOTOS = "photos"
    }

    @GET(USERS)
    suspend fun getUser(@Query(ID) userId: Int): List<UserItem>

    @GET(ALBUMS)
    suspend fun getAlbums(@Query(USER_ID) userId: Int): List<AlbumItem>

    @GET(PHOTOS)
    suspend fun getPhotos(@Query(ALBUM_ID) albumId: Int): List<PhotoItem>
}