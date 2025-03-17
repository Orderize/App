package com.orderize.orderize.model

data class MockPizza(
    val id: Long = 0L,
    val name: String = "Mussarela",
    val observations: String = "Sem Orégano",
    val done: Boolean = false,
    val qtt: Int = 2,
    val isChecked: Boolean = false
)
