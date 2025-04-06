package com.orderize.orderize.model

import androidx.compose.ui.graphics.Color

enum class TableStatus{
    PENDENTE,
    EM_PREPARO,
    DISPONIVEL
}

data class MockTable(
    val id: Long,
    val number: Number,
    val status: TableStatus
)
