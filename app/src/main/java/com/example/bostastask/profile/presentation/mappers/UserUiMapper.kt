package com.example.bostastask.profile.presentation.mappers

import com.example.bostastask.profile.domain.models.UserDomainModel
import com.example.bostastask.profile.presentation.models.UserUiModel

fun UserDomainModel.toUserUiModel(): UserUiModel =
    UserUiModel(
        userId = this.userId,
        userName = this.userName,
        address = "${this.address.street}, ${this.address.suite}, ${this.address.city}, ${this.address.zipCode}"
    )