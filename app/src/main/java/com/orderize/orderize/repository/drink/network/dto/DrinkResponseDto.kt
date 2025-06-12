package com.orderize.orderize.repository.drink.network.dto

import com.google.gson.annotations.SerializedName

data class DrinkResponseDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("milimeters") val milimeters: Double,
    @SerializedName("price") val price: Double
)