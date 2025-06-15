package com.orderize.orderize.ui.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.orderize.orderize.model.Order

class SharedOrderViewModel : ViewModel() {
    var selectedOrder by mutableStateOf<Order?>(null)
        private set

    fun setOrder(order: Order) {
        selectedOrder = order
    }
}