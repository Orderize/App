package com.orderize.orderize.ui.profile

data class ProfileScreenUiState(
    val name: String = "",
    val role: String = "",
    val mostCookedPizza: String = "",
    val qttCookedPizzas: Int = 0,
    val averageTimeValue: Int = 0,
    val averageTimeText: String = "",
    val companyName: String = ""
)
