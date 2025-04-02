package com.orderize.orderize.ui.history

import androidx.lifecycle.ViewModel
import com.orderize.orderize.ui.pizzaiolo_home.mockOrders
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HistoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HistoryScreenUiState())
    val uiState: StateFlow<HistoryScreenUiState> = _uiState

    init {
        loadOrders()
    }

    private fun loadOrders() {
        _uiState.value = HistoryScreenUiState(
            items = mockOrders
        )
    }

    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }
}

