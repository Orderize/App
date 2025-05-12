package com.orderize.orderize.repository.drink.network

import com.orderize.orderize.BuildConfig
import com.orderize.orderize.BuildConfig.ORDERIZE_BASE_URL
import com.orderize.orderize.repository.drink.network.dto.DrinkResponseDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface IDrinkService {
    @GET("drinks?name=")
    suspend fun getAllDrinks(): Response<List<DrinkResponseDto>>
}

