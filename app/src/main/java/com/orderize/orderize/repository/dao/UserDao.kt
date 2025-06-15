package com.orderize.orderize.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orderize.orderize.model.User
import com.orderize.orderize.repository.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM User WHERE apiToken = :token LIMIT 1")
    suspend fun getUserByApiToken(token: String): User?

    @Query("DELETE FROM User WHERE apiToken = :token")
    suspend fun deleteUserByApiToken(token: String): Int?

    @Delete
    suspend fun deleteUser(user: UserEntity): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity): Long?

}