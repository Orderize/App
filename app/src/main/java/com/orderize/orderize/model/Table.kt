package com.orderize.orderize.model

enum class TableStatus{
    PENDENTE,
    EM_PREPARO,
    DISPONIVEL
}

data class Table(
    val id: Long,
    val number: Int,
    val status: TableStatus
)
