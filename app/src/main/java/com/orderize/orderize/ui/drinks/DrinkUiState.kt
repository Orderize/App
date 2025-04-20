package com.orderize.orderize.ui.drinks

data class DrinkUiState(
    val items: List<Pair<String, String>> = emptyList(),
    val searchQuery: String = "",
    val selectedDrinks: List<String> = emptyList()

)
