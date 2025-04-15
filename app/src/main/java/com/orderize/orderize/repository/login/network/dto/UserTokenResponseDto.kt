package com.orderize.orderize.repository.login.network.dto

import com.google.gson.annotations.SerializedName

data class UserTokenResponseDto(
    @SerializedName("token") val token: String
)
