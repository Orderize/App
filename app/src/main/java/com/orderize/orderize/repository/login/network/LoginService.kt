package com.orderize.orderize.repository.login.network

import android.util.Log
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.repository.login.network.dto.LoginRequestDto
import com.orderize.orderize.repository.login.network.dto.UserResponseDto
import com.orderize.orderize.repository.login.network.dto.UserTokenResponseDto

class LoginService(
    private val service: ILoginService
) {

    suspend fun authenticateUser(
        loginRequestDto: LoginRequestDto
    ): NetResource<UserTokenResponseDto> {
        return try {
            val response = service.authenticateUser(
                loginRequestDto
            )

            if (response.isSuccessful && response.body() != null) {
                NetResource.Success(response.body()!!)
            } else {
                var message = response.code().toString()
                if (response.body() != null) {
                    message += response.body()
                }
                if (response.message().isNotBlank()) {
                    message += response.message()
                }
                if (response.errorBody() != null) {
                    message += response.errorBody()
                }
                Log.i("LoginService", message)
                NetResource.Fail(message)
            }
        } catch (exception: Exception) {
            Log.i("LoginService", "${exception.message}")
            NetResource.Fail(exception.message ?: exception.toString())
        }
    }

    suspend fun getUserByEmail(
        email: String,
        token: String
    ): NetResource<UserResponseDto> {
        return try {
            val response = service.getUserByEmail(email, token)

            if (response.isSuccessful && response.body() != null) {
                NetResource.Success(response.body()!!.first())
            } else {
                var message = response.code().toString()
                if (response.body() != null) {
                    message += response.body()
                }
                if (response.message().isNotBlank()) {
                    message += response.message()
                }
                if (response.errorBody() != null) {
                    message += response.errorBody()
                }
                NetResource.Fail(message)
            }
        } catch (exception: Exception) {
            NetResource.Fail(exception.message ?: exception.toString())
        }
    }

}