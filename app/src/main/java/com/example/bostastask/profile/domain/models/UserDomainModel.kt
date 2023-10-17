package com.example.bostastask.profile.domain.models

data class UserDomainModel(
    val userId: Int,
    val userName: String,
    val address: AddressDomainModel
)
