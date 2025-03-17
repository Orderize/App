package com.orderize.orderize.ui.orderdetails

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderDetailsViewModel: ViewModel() {

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
                    _uiState.update {
                        it.copy(
                            order = it.order.copy(status = "Em Preparo"),
                            showConfirmationDialog = !it.showConfirmationDialog
                        )
                    }
                },
                onFinishClick = {
                    _uiState.update {
                        it.copy(
                            order = it.order.copy(
                                status = "Finalizado"
                            ),
                            showConfirmationDialog = !it.showConfirmationDialog,
                            orderFinished = true
                        )
                    }
                },
                onItemCompleted = {
                    // TOOD: Vai atualiza o item
                },
                onCheckBoxClicked = {
                    val updatedItems = _uiState.value.order.items.map { pizza ->
                        if (pizza == it) {
                            pizza.copy(isChecked = !pizza.isChecked)
                        } else {
                            pizza
                        }
                    }
                    _uiState.update {
                        it.copy(
                            order = it.order.copy(items = updatedItems)
                        )
                    }
                }
            )
        }
    }

    fun findOrderById(itemId: Long) {
        // TODO: Buscar o item no back e setar no state, para ser utilizado na tela
    }
}