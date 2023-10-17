package com.example.bostastask.profile.presentation.mappers

import com.example.bostastask.profile.domain.models.AlbumDomainModel
import com.example.bostastask.profile.presentation.models.AlbumUiModel

fun AlbumDomainModel.toAlbumUiModel(): AlbumUiModel =
    AlbumUiModel(id = this.id, title = this.title)