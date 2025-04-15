package com.orderize.orderize.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val email: String,
    val companyId: Long,
    val role: String,
    val apiToken: String = ""
)
