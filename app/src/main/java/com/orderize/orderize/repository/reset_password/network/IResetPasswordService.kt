package com.orderize.orderize.repository.reset_password.network

import com.orderize.orderize.repository.reset_password.network.dto.ResetPasswordRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IResetPasswordService {
    @POST("auth/reset-password")
    suspend fun resetPasswordUser(
        @Body email: ResetPasswordRequestDto
    ): Response<Void>
}