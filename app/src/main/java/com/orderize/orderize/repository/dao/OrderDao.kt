package com.orderize.orderize.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.orderize.orderize.repository.entity.OrderEntity

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrder(order: OrderEntity): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrders(orders: List<OrderEntity>): List<Long>

    @Query("SELECT * FROM `Order`")
    suspend fun getAllOrders(): List<OrderEntity>

    @Delete
    suspend fun deleteOrder(order: OrderEntity): Int?

    @Query("DELETE FROM `Order`")
    suspend fun deleteAllOrders()

    @Query("DELETE FROM `Order` WHERE id = :orderId")
    suspend fun deleteOrderById(orderId: Long): Int?

    @Update
    suspend fun updateOrder(order: OrderEntity): Int?

    @Query("SELECT EXISTS(SELECT 1 FROM `Order` WHERE id = :orderId)")
    suspend fun existsById(orderId: Long): Boolean

}