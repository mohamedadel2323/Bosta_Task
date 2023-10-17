package com.example.bostastask.profile.data.mappers

import com.example.bostastask.profile.data.dto.user.Address
import com.example.bostastask.profile.data.dto.user.UserItem
import com.example.bostastask.profile.domain.models.AddressDomainModel
import com.example.bostastask.profile.domain.models.UserDomainModel

fun UserItem.toUserDomainModel(): UserDomainModel =
    UserDomainModel(
        userId = this.id ?: 1,
        userName = this.userName ?: "",
        address = this.address?.toAddressDomainModel() ?: AddressDomainModel(
            "",
            "",
            "",
            ""
        )
    )

fun Address.toAddressDomainModel(): AddressDomainModel =
    AddressDomainModel(
        city = this.city,
        street = this.street,
        suite = this.suite,
        zipCode = this.zipCode
    )