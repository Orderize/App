package com.orderize.orderize.repository.drink.local

import androidx.room.Dao
import androidx.room.Query
import com.orderize.orderize.repository.entity.DrinkEntity

@Dao
interface DrinkDao {
    @Query("SELECT * FROM drink")
    fun getAllDrinks(): List<DrinkEntity>

}