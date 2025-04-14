package com.orderize.orderize.repository.login.network

import com.orderize.orderize.repository.login.network.dto.LoginRequestDto
import com.orderize.orderize.repository.login.network.dto.UserResponseDto
import com.orderize.orderize.repository.login.network.dto.UserTokenResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ILoginService {

    @POST("auth/login")
    suspend fun authenticateUser(
        @Body login: LoginRequestDto
    ): Response<UserTokenResponseDto>

    @GET("users")
    suspend fun getUserByEmail(
        @Query("email") email: String,
        @Header("Authorization") token: String
    ): Response<List<UserResponseDto>>

}