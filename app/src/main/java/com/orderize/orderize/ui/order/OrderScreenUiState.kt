package com.orderize.orderize.ui.order

import androidx.compose.runtime.mutableStateListOf
import com.orderize.orderize.model.MockPizza

data class OrderScreenUiState(
//    val pizza: String = "",
//    val bebida: String = "",
//    val mesa: String = "",
//    val nomeCliente: String = "",
////    val pizzas = listOf(PizzaItem(...)),
////    val bebidas = listOf(BebidaItem(...)),
//    val total: Int = 0,
    val pizzas: MutableList<MockPizza> = mutableStateListOf()
){
}