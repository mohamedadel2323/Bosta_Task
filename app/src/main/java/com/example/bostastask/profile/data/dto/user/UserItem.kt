package com.example.bostastask.profile.data.dto.user

import com.google.gson.annotations.SerializedName

data class UserItem(
    val address: Address?,
    val company: Company?,
    val email: String?,
    val id: Int?,
    val name: String?,
    val phone: String?,
    @SerializedName("username")
    val userName: String?,
    @SerializedName("website")
    val webSite: String?
)