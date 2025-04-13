package com.orderize.orderize.model

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val companyId: Long,
    val role: String
)
