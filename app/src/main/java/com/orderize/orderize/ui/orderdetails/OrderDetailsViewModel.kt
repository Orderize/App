package com.orderize.orderize.ui.orderdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.model.Order
import com.orderize.orderize.model.enum.OrderStatus
import com.orderize.orderize.repository.order.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderDetailsViewModel(
    private val repository: OrderRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<OrderDetailsUiState> = MutableStateFlow(
        OrderDetailsUiState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onShowConfirmationDialogChange = {
                    _uiState.update {
                        it.copy(
                            showConfirmationDialog = !it.showConfirmationDialog
                        )
                    }
                },
                onStartClick = {
                    viewModelScope.launch(Dispatchers.IO) {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loadingStatusChange = true,
                                showConfirmationDialog = false
                            )
                        }
                        val orderToUpdate = _uiState.value.order
                        val apiOrder = repository.putOrderInPreparingStatus(orderToUpdate.id)
                        if (apiOrder is NetResource.Success) {
                            _uiState.update {
                                it.copy(
                                    order = apiOrder.data,
                                    showConfirmationDialog = false,
                                    showSnackbar = true,
                                    snackbarMessage = "Pedido Em Preparo",
                                    loadingStatusChange = false
                                )
                            }
                            repository.saveOrderLocal(apiOrder.data)
                        } else {
                            _uiState.update {
                                it.copy(
                                    showConfirmationDialog = false,
                                    showSnackbar = true,
                                    snackbarMessage = "Falha ao atualizar o pedido, tente novamente",
                                    loadingStatusChange = false
                                )
                            }
                        }
                    }
                },
                onFinishClick = {
                    viewModelScope.launch(Dispatchers.IO) {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loadingStatusChange = true,
                                showConfirmationDialog = false
                            )
                        }
                        val orderToUpdate = _uiState.value.order
                        val apiOrder = repository.putOrderInAvailableStatus(orderToUpdate.id)
                        if (apiOrder is NetResource.Success) {
                            _uiState.update {
                                it.copy(
                                    order = apiOrder.data,
                                    showConfirmationDialog = false,
                                    showSnackbar = true,
                                    snackbarMessage = "Pedido Concluído",
                                    loadingStatusChange = false,
                                    orderFinished = true
                                )
                            }
                            repository.saveOrderLocal(apiOrder.data)
                        } else {
                            _uiState.update {
                                it.copy(
                                    showConfirmationDialog = false,
                                    showSnackbar = true,
                                    snackbarMessage = "Falha ao atualizar o pedido, tente novamente",
                                    loadingStatusChange = false
                                )
                            }
                        }
                    }
                },
                onItemCompleted = {
                    // TOOD: Vai atualiza o item
                },
                onCheckBoxClicked = {
                    val updatedItems = _uiState.value.order.pizzas.map { pizza ->
                        if (pizza == it) {
                            pizza.copy(isChecked = !pizza.isChecked)
                        } else {
                            pizza
                        }
                    }
                    _uiState.update {
                        it.copy(
                            order = it.order.copy(pizzas = updatedItems)
                        )
                    }
                }
            )
        }
    }

    fun hideSnackbar() {
        _uiState.update {
            it.copy(showSnackbar = false)
        }
    }

    fun setOrder(order: Order) {
        Log.i("OrderDetailsViewModel", order.toString())
        _uiState.update {
            it.copy(order = order)
        }
    }

}