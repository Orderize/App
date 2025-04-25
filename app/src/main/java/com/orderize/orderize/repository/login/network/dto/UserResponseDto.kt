package com.orderize.orderize.repository.login.network.dto

import com.google.gson.annotations.SerializedName

data class UserResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("enterprise") val companyId: UserResponseEnterpriseDto,
    @SerializedName("roles") val roles: List<UserResponseRoleDto>
)

data class UserResponseEnterpriseDto(
    @SerializedName("id") val id: Long
)

data class UserResponseRoleDto(
    @SerializedName("name") val name: String
)