package com.orderize.orderize.repository.order.network.dto

import com.google.gson.annotations.SerializedName

data class OrderRequestDto(
    @SerializedName("client") val clientId: Long,
    @SerializedName("responsible") val responsible: Long,
    @SerializedName("pizzas") val pizzas: List<Long>,
    @SerializedName("drinks") val drinks: List<Long>,
    @SerializedName("type") val type: String,
    @SerializedName("freight") val freight: Double,
    @SerializedName("estimatedTime") val estimatedMinutes: Long,
    @SerializedName("table") val table: Long,
    @SerializedName("status") val status: String
)
