package com.orderize.orderize.repository

open class CommonRepository(
    private val dataStore: AppDataStore
) {

    suspend fun getApiToken() = dataStore.getToken()

}