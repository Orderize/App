package com.orderize.orderize.ui.history

import com.orderize.orderize.model.MockOrder
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.model.Order

data class HistoryScreenUiState(
    val items: NetResource<List<Order>> = NetResource.Processing,
    val searchQuery: String = ""
)




