package com.orderize.orderize.repository.order.network

import com.orderize.orderize.repository.order.network.dto.OrderRequestDto
import com.orderize.orderize.repository.order.network.dto.OrderResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrderService {

    @GET("orders/status/preparation")
    suspend fun getAllPreparingOrders(): Response<List<OrderResponseDto>>

    @GET("orders/status/pending")
    suspend fun getAllPendingOrders(): Response<List<OrderResponseDto>>

    @GET("orders/status/available")
    suspend fun getAllAvailableOrders(): Response<List<OrderResponseDto>>

    @GET("orders/today")
    suspend fun getAllTodayOrders(): Response<List<OrderResponseDto>>

    @PUT("orders/{id}/status/preparation")
    suspend fun putOrderInPreparingStatus(@Path("id") id: Long): Response<OrderResponseDto>

    @PUT("orders/{id}/status/pending")
    suspend fun putOrderInPendingStatus(@Path("id") id: Long): Response<OrderResponseDto>

    @PUT("orders/{id}/status/available")
    suspend fun putOrderInAvailableStatus(@Path("id") id: Long): Response<OrderResponseDto>

    @POST("orders")
    suspend fun postOrder(@Body order: OrderRequestDto): Response<OrderResponseDto>

}