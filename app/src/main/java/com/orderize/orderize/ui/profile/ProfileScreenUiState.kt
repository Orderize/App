package com.orderize.orderize.ui.profile

data class ProfileScreenUiState(
    val name: String = "",
    val role: String = "",
    val averageTimeValue: Int = 0,
    val averageTimeText: String = "",
    val companyName: String = "",
    val finishedOrders: Long = 0L,
    val pendingOrders: Long = 0L,
    val preparingOrders: Long = 0L,
    val qttPizzas: Long = 0L,
    val logout: () -> Unit = {},
    val userDisconnected: Boolean = false,
    val getOrdersData: () -> Unit = {}
)
