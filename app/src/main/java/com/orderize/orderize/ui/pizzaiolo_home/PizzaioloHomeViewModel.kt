package com.orderize.orderize.ui.pizzaiolo_home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.model.Order
import com.orderize.orderize.model.enum.OrderStatus
import com.orderize.orderize.repository.order.OrderRepository
import com.orderize.orderize.ui.common.SharedOrderViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin

class PizzaioloHomeViewModel(
    private val repository: OrderRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<PizzaioloScreenUiState> = MutableStateFlow(
        PizzaioloScreenUiState()
    )
    val uiState get() = _uiState.asStateFlow()

    private val orders = mutableStateOf<NetResource<List<Order>>>(NetResource.Processing)

    fun getOrders(withDelay: Boolean = false) {
        Log.i("PizzaioloHomeViewModel", "gettingOrders")
        viewModelScope.launch(Dispatchers.IO) {
            orders.value = NetResource.Processing
            _uiState.update { currentState ->
                currentState.copy(
                    items = orders.value,
                    getOrders = { getOrders(withDelay = true) }
                )
            }
            if (withDelay) {
                delay(500)
            }

            val apiOrders = repository.getAllTodayOrders()
            if (apiOrders is NetResource.Success) {
                val filteredOrders = apiOrders.data.filter { it.status.isNotBlank() && it.status != OrderStatus.FINALIZADO.databaseName }
                if (filteredOrders.isNotEmpty()) {
                    orders.value = NetResource.Success(filteredOrders)
                    repository.saveOrdersLocal(apiOrders.data)
                } else {
                    orders.value = NetResource.Fail("Nenhum pedido encontrado")
                }
            } else {
                orders.value = NetResource.Fail("Um erro ocorreu ao buscar os Pedidos")
            }

            _uiState.update { currentState ->
                currentState.copy(
                    items = orders.value,
                    getOrders = { getOrders(withDelay = true) }
                )
            }
        }
    }
}