package com.orderize.orderize.model

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
