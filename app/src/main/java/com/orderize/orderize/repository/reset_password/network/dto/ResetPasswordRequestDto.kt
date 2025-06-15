package com.orderize.orderize.repository.reset_password.network.dto

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequestDto (
    @SerializedName("email") val  email: String
)