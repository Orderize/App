package com.orderize.orderize.model

import java.sql.Time
import java.time.LocalTime

data class MockOrder(
    val id: Int = 0,
    val type: String = "Salão",
    val status: String = "Pendente",
    val createdTime: LocalTime = LocalTime.of(12, 0)
)
