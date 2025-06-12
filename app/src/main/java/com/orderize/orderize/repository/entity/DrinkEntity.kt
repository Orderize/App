package com.orderize.orderize.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink")
data class DrinkEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val description: String,
    val milimeters: Double,
    val price: Double
)
