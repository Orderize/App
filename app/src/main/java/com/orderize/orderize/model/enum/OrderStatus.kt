package com.orderize.orderize.model.enum

enum class OrderStatus(val displayName: String, val databaseName: String){
    PENDENTE("Pendente", "PENDENTE"),
    EM_PREPARO("Em Preparo", "EM PREPARO"),
    FINALIZADO("Finalizado", "DISPONIVEL")
}