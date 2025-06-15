package com.orderize.orderize.ui.orderdetails

import com.orderize.orderize.BuildConfig
import com.orderize.orderize.model.Drink
import com.orderize.orderize.model.Flavor
import com.orderize.orderize.model.MockOrder
import com.orderize.orderize.model.MockPizza
import com.orderize.orderize.model.Order
import com.orderize.orderize.model.Pizza
import com.orderize.orderize.model.User
import com.orderize.orderize.model.enum.OrderStatus
import java.time.LocalTime

data class OrderDetailsUiState(
    val order: Order = mockOrder,
    val onStartClick: () -> Unit = {},
    val onFinishClick: () -> Unit = {},
    val onItemCompleted: () -> Unit = {},
    var showConfirmationDialog: Boolean = false,
    val onShowConfirmationDialogChange: () -> Unit = { },
    val orderFinished: Boolean = false,
    val onCheckBoxClicked: (Pizza) -> Unit = {},
    val showSnackbar: Boolean = false,
    val snackbarMessage: String = "foi iniciado",
    val loadingStatusChange: Boolean = false
) {
    val isFinishButtonEnabled = order.pizzas.filter { !it.isChecked }.isEmpty()
    val showCheckBox = order.status != OrderStatus.PENDENTE.databaseName
}

private val mockOrder = Order(
    id = 1L,
    client = User(
        id = 101L,
        name = "Maria Oliveira",
        email = "maria.oliveira@email.com",
        role = "",
        companyId = 1L
    ),
    pizzas = listOf(
        Pizza(
            id = 201L,
            name = "Meia Calabresa Meia Portuguesa",
            price = 55.00,
            observations = "Sem cebola na portuguesa",
            flavors = listOf(
                Flavor(
                    id = 301L,
                    name = "Calabresa",
                    description = "Calabresa fatiada com cebola e orégano",
                    price = 0.0
                ),
                Flavor(
                    id = 302L,
                    name = "Portuguesa",
                    description = "Presunto, queijo, ovos, cebola, pimentão e azeitonas",
                    price = 0.0
                )
            ),
            border = "Catupiry",
            size = "Grande",
            mass = "Tradicional"
        )
    ),
    drinks = listOf(
        Drink(
            id = 401L,
            name = "Coca-Cola",
            description = "Refrigerante de Cola",
            price = 8.00,
            milimeters = 600
        ),
        Drink(
            id = 402L,
            name = "Suco de Laranja",
            description = "Suco natural de laranja",
            price = 7.00,
            milimeters = 500
        )
    ),
    date = LocalTime.now(),
    type = "Delivery",
    price = 55.00,
    table = 0L,
    status = "PENDENTE",
    lastModified = LocalTime.now()
)
