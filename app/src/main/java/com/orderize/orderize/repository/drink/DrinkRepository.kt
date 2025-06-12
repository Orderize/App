package com.orderize.orderize.repository.drink

import android.util.Log
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.repository.AppDataStore
import com.orderize.orderize.repository.CommonRepository
import com.orderize.orderize.repository.drink.network.DrinkService
import com.orderize.orderize.repository.drink.network.IDrinkService
import com.orderize.orderize.repository.drink.network.dto.DrinkResponseDto

class DrinkRepository (
    private val service: DrinkService,
    private val dataStore: AppDataStore
): CommonRepository(dataStore){
    suspend fun getAllDrinks(): NetResource<List<DrinkResponseDto>> {
        return service.getAllDrinks()
    }
}