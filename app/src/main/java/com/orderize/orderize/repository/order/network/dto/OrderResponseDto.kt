package com.orderize.orderize.repository.order.network.dto

import com.google.gson.annotations.SerializedName

data class OrderResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("client") val client: OrderClientResponseDto,
    @SerializedName("pizzas") val pizzas: List<OrderPizzaResponseDto>,
    @SerializedName("drinks") val drinks: List<OrderDrinkResponseDto>,
    @SerializedName("datetime") val date: String,
    @SerializedName("type") val type: String,
    @SerializedName("price") val price: Double,
    @SerializedName("table") val table: Long?,
    @SerializedName("status") val status: String?,
    @SerializedName("lastModified") val lastModified: String?
)

data class OrderClientResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String
)

data class OrderPizzaResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double,
    @SerializedName("observations") val observations: String,
    @SerializedName("flavors") val flavors: List<PizzaFlavorResponseDto>,
    @SerializedName("border") val border: String,
    @SerializedName("size") val size: String,
    @SerializedName("mass") val mass: String
)

data class PizzaFlavorResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double
)

data class OrderDrinkResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double,
    @SerializedName("milimeters") val milimeters: Long
)
