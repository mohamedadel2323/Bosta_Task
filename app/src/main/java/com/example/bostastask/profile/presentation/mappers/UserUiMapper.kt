package com.example.bostastask.profile.presentation.mappers

import com.example.bostastask.profile.domain.models.UserDomainModel
import com.example.bostastask.profile.presentation.models.UserUiModel

fun UserDomainModel.toUserUiModel(): UserUiModel =
    UserUiModel(
        userId = this.userId,
        userName = this.userName,
        address = formatAddress(this)
    )

private fun formatAddress(user: UserDomainModel) =
    "${user.address.street}, ${user.address.suite}, ${user.address.city}, ${user.address.zipCode}"