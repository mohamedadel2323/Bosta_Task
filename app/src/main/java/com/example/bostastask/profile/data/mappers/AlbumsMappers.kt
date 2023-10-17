package com.example.bostastask.profile.data.mappers

import com.example.bostastask.profile.data.dto.album.AlbumItem
import com.example.bostastask.profile.data.dto.album.AlbumResponse
import com.example.bostastask.profile.domain.models.AlbumDomainModel

fun AlbumItem.toAlbumDomainModel(): AlbumDomainModel =
    AlbumDomainModel(id = this.id ?: 1, title = this.title ?: "")

fun AlbumResponse.toAlbumDomainResponse() : List<AlbumDomainModel> =
    this.map { it.toAlbumDomainModel() }