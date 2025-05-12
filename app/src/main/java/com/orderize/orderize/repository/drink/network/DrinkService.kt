package com.orderize.orderize.repository.drink.network

import com.orderize.orderize.model.NetResource
import com.orderize.orderize.repository.drink.network.dto.DrinkResponseDto

class DrinkService (
    private val service: IDrinkService
) {
    suspend fun getAllDrinks(): NetResource<List<DrinkResponseDto>>{
        return try{
            val response = service.getAllDrinks()

            if (response.isSuccessful && response.body() != null) {
                NetResource.Success(response.body()!!)
            } else {
                var message = response.code().toString()
                if (response.message().isNotBlank()) {
                    message += response.message()
                }
                if (response.errorBody() != null) {
                    message += response.errorBody()?.string() ?: ""
                }
                NetResource.Fail(message)
            }
        } catch (exception: Exception) {
            NetResource.Fail(exception.message ?: exception.toString())
        }
    }
}