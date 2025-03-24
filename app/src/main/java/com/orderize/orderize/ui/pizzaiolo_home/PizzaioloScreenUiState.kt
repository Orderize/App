package com.orderize.orderize.ui.pizzaiolo_home

import com.orderize.orderize.model.MockOrder

data class PizzaioloScreenUiState(
    val items: List<MockOrder> = emptyList()
)
