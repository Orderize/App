package com.orderize.orderize.ui.history

import com.orderize.orderize.model.MockOrder

data class HistoryScreenUiState(
    val items: List<MockOrder> = emptyList(),
    val searchQuery: String = ""
)




