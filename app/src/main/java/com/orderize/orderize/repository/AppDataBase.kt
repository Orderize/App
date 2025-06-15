package com.orderize.orderize.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.orderize.orderize.repository.dao.OrderDao
import com.orderize.orderize.repository.dao.UserDao
import com.orderize.orderize.repository.entity.OrderEntity
import com.orderize.orderize.repository.entity.UserEntity

const val DATA_BASE_NAME = "orderize.db"

@Database(
    entities = [UserEntity::class, OrderEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {

    abstract val userDao: UserDao
    abstract val orderDao: OrderDao

}