package com.orderize.orderize.ui.drinks

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DrinkViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        DrinkUiState(
            items = listOf(
                "Coca-cola, 1L" to "R$8,00",
                "Sprite, 600ml" to "R$5,40",
                "Guaraná, 2L" to "R$13,00",
                "Fanta-laranja, 600ml" to "R$4,00",
                "Fanta-uva, 600ml" to "R$4,00"
            )
        )
    )
    val uiState: StateFlow<DrinkUiState> = _uiState

    fun updateSearchQuery(newQuery: String) {
        _uiState.value = _uiState.value.copy(searchQuery = newQuery)
    }
}
