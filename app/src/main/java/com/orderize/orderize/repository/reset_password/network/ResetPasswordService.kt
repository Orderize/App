package com.orderize.orderize.repository.reset_password.network

import android.util.Log
import com.orderize.orderize.repository.reset_password.network.dto.ResetPasswordRequestDto

class ResetPasswordService (private val service: IResetPasswordService) {
    suspend fun resetPasswordUser(
        resetPasswordRequestDto: ResetPasswordRequestDto
    ) : Boolean {
        return try {
            val response = service.resetPasswordUser(
                resetPasswordRequestDto
            )

            if (response.isSuccessful) {
                println("Redefinição de senha solicitada com sucesso!")
                return true
            }else{
                var message = "Erro ao solicitar redefinição de senha: ${response.code()}"

                if (response.message().isNotBlank()) {
                    message += " - ${response.message()}"
                }
                if (response.errorBody() != null) {
                    message += " - Erro Body: ${response.errorBody()!!.string()}"
                }

                Log.e("ResetPasswordService", message)
                return false
            }
        }catch (exception: Exception) {
        Log.e("ResetPasswordService", "Exceção ao chamar resetPassworduser: ${exception.message}", exception)
            return false
        }
    }
}