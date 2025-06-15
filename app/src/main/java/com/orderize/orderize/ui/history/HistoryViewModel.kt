package com.orderize.orderize.ui.history

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.model.Order
import com.orderize.orderize.model.enum.OrderStatus
import com.orderize.orderize.repository.order.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: OrderRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HistoryScreenUiState> = MutableStateFlow(
        HistoryScreenUiState()
    )
    val uiState get() = _uiState.asStateFlow()

    private val orders = mutableStateOf<NetResource<List<Order>>>(NetResource.Processing)

    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun getOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            orders.value = NetResource.Processing
            _uiState.update { currentState ->
                currentState.copy(
                    items = orders.value,
                )
            }

            val dbOrders = repository.getOrdersLocal().filter { it.status == OrderStatus.FINALIZADO.databaseName }
            if (dbOrders.isNotEmpty()) {
                    orders.value = NetResource.Success(dbOrders)
            } else {
                orders.value = NetResource.Fail("Nenhum pedido encontrado")
            }

            _uiState.update { currentState ->
                currentState.copy(
                    items = orders.value,
                )
            }
        }
    }
}

