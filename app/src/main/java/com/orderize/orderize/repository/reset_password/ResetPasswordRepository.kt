package com.orderize.orderize.repository.reset_password

import android.util.Log
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.repository.AppDataStore
import com.orderize.orderize.repository.CommonRepository
import com.orderize.orderize.repository.reset_password.network.ResetPasswordService
import com.orderize.orderize.repository.reset_password.network.dto.ResetPasswordRequestDto

class ResetPasswordRepository (
    private val service: ResetPasswordService,
    private val dataStore: AppDataStore
) : CommonRepository(dataStore){

    suspend fun resetPassword(
        email: String
    ) : NetResource<Boolean> {
        val isResetSucessful = service.resetPasswordUser(
            ResetPasswordRequestDto(email)
        )

        if (isResetSucessful) {
            Log.i("ResetPasswordRepository", "Requisição de redefinição de senha bem-sucedida para: $email")
            return NetResource.Success(true)
        }else{
            Log.e("ResetPasswordRepository", "Falha na requisição de redefinição de senha para: $email")
            return NetResource.Fail("Não foi possível solicitar a redefinição de senha. Verifique o e-mail ou tente novamente.")
        }
    }
}