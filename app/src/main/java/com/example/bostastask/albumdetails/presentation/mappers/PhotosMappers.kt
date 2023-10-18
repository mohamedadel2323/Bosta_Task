package com.example.bostastask.albumdetails.presentation.mappers

import com.example.bostastask.albumdetails.domain.models.PhotoDomainModel
import com.example.bostastask.albumdetails.presentation.models.PhotoUiModel

fun PhotoDomainModel.toPhotoUiModel(): PhotoUiModel =
    PhotoUiModel(id = this.id, thumbnailUrl = this.thumbnailUrl, url = this.url, title = this.title)
