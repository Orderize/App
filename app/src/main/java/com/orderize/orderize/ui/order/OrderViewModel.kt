package com.orderize.orderize.ui.order

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.orderize.orderize.model.MockPizza
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderViewModel : ViewModel() {
    private val _uiState : MutableStateFlow<OrderScreenUiState> = MutableStateFlow(
        OrderScreenUiState())
    val uiState get() = _uiState.asStateFlow()
}