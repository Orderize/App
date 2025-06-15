package com.orderize.orderize.ui.pizzaiolo_home

import com.orderize.orderize.model.NetResource
import com.orderize.orderize.model.Order

data class PizzaioloScreenUiState(
    val items: NetResource<List<Order>> = NetResource.Processing,
    val getOrders: () -> Unit = {},
    val isRefreshing: Boolean = false
)
