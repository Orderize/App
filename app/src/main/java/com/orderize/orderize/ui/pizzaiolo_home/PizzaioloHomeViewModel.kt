package com.orderize.orderize.ui.pizzaiolo_home

import androidx.lifecycle.ViewModel
import com.orderize.orderize.model.MockOrder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalTime

class PizzaioloHomeViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<PizzaioloScreenUiState> = MutableStateFlow(
        PizzaioloScreenUiState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                items = mockOrders
            )
        }
    }
}

val mockOrders = listOf(
    MockOrder(
        1,
        "Delivery",
        "Pendente",
        LocalTime.of(11, 20)
    ),
    MockOrder(
        2,
        "Salão",
        "Em Preparo",
        LocalTime.of(20, 25)
    ),
    MockOrder(
        3,
        "Delivery",
        "Pendente",
        LocalTime.of(12, 20)
    ),
    MockOrder(
        234,
        "Salão",
        "Pendente",
        LocalTime.of(15, 20)
    ),
    MockOrder(
        654,
        "Delivery",
        "Em Preparo",
        LocalTime.of(11, 20)
    ),
    MockOrder(
        645,
        "Salão",
        "Pendente",
        LocalTime.of(11, 0)
    ),
    MockOrder(
        1,
        "Delivery",
        "Pendente",
        LocalTime.of(11, 20)
    ),
    MockOrder(
        2,
        "Salão",
        "Em Preparo",
        LocalTime.of(20, 25)
    ),
    MockOrder(
        3,
        "Delivery",
        "Pendente",
        LocalTime.of(12, 20)
    ),
    MockOrder(
        234,
        "Salão",
        "Pendente",
        LocalTime.of(15, 20)
    ),
    MockOrder(
        654,
        "Delivery",
        "Em Preparo",
        LocalTime.of(11, 20)
    ),
    MockOrder(
        645,
        "Salão",
        "Pendente",
        LocalTime.of(11, 0)
    ),
    MockOrder(
        1,
        "Delivery",
        "Pendente",
        LocalTime.of(11, 20)
    ),
    MockOrder(
        2,
        "Salão",
        "Em Preparo",
        LocalTime.of(20, 25)
    ),
    MockOrder(
        3,
        "Delivery",
        "Pendente",
        LocalTime.of(12, 20)
    ),
    MockOrder(
        234,
        "Salão",
        "Pendente",
        LocalTime.of(15, 20)
    ),
    MockOrder(
        654,
        "Delivery",
        "Em Preparo",
        LocalTime.of(11, 20)
    ),
    MockOrder(
        645,
        "Salão",
        "Pendente",
        LocalTime.of(11, 0)
    ),
)

/*
data class MockOrder(
    val id: Int = 0,
    val type: String = "Salão",
    val status: String = "Pendente",
    val createdTime: LocalTime = LocalTime.of(12, 0)
)
 */