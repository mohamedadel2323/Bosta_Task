package com.example.bostastask.albumdetails.data.mappers

import com.example.bostastask.albumdetails.data.dto.photos.PhotoItem
import com.example.bostastask.albumdetails.data.dto.photos.PhotoResponse
import com.example.bostastask.albumdetails.domain.models.PhotoDomainModel

fun PhotoItem.toPhotoDomainModel(): PhotoDomainModel =
    PhotoDomainModel(
        id = this.id ?: 1,
        thumbnailUrl = this.thumbnailUrl ?: "",
        title = this.title ?: "",
        url = this.url ?: ""
    )

fun PhotoResponse.toPhotoDomainResponse(): List<PhotoDomainModel> =
    this.map { it.toPhotoDomainModel() }