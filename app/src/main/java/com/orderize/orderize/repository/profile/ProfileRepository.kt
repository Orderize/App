package com.orderize.orderize.repository.profile

import android.util.Log
import com.orderize.orderize.model.User
import com.orderize.orderize.repository.AppDataStore
import com.orderize.orderize.repository.CommonRepository
import com.orderize.orderize.repository.login.local.UserDao

class ProfileRepository(
    private val dataStore: AppDataStore,
    private val userDao: UserDao
): CommonRepository(dataStore) {

    suspend fun getUserData(): User? {
        try {
            val token = dataStore.getToken()
            if (token != null) {
                return userDao.getUserByApiToken(dataStore.getToken()!!)
            } else {
                Log.i("ProfileRepository", "Erro ao token")
                return null
            }
        } catch (e: Exception) {
            Log.i("ProfileRepository", "Erro ao buscar UserData ${e.message}")
            return null
        }
    }

    suspend fun logout(): Boolean {
        try {
            val token = dataStore.getToken()
            if (token != null) {
                userDao.deleteUserByApiToken(token)
                dataStore.removeToken()
                return true
            }
            return false
        } catch (e: Exception) {
            return false
        // TODO: Tratar aqui melhorar função e talz, sem tempo por causa da apresentação
            Log.i("ProfileRepository", "Erro em logout ${e.message}")
        }
    }
}