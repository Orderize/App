package com.orderize.orderize.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orderize.orderize.repository.drink.local.DrinkDao
import com.orderize.orderize.repository.entity.DrinkEntity
import com.orderize.orderize.repository.entity.UserEntity
import com.orderize.orderize.repository.login.local.UserDao

const val DATA_BASE_NAME = "orderize.db"

@Database(
    entities = [UserEntity::class, DrinkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase() {

    abstract val userDao: UserDao
    abstract val drinkDao: DrinkDao

}