package com.orderize.orderize.ui.drinks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orderize.orderize.repository.drink.DrinkRepository
import com.orderize.orderize.model.NetResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DrinkViewModel(
    private val drinkRepository: DrinkRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<DrinkUiState> =
        MutableStateFlow(DrinkUiState())

    val uiState: StateFlow<DrinkUiState> = _uiState


    init {
        loadDrinks()
    }

    private fun loadDrinks() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            when (val result = drinkRepository.getAllDrinks()) {
                is NetResource.Success -> {
                    val drinksList = result.data.map { drink -> drink.name to "R$%.2f".format(drink.price) }
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        items = drinksList
                    )
                    Log.d("Response", drinksList.size.toString())
                }
                is NetResource.Fail -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                is NetResource.Processing -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }

    fun updateSearchQuery(newQuery: String) {
        _uiState.value = _uiState.value.copy(searchQuery = newQuery)
    }

    fun toggleDrinkSelection(drink: String) {
        val currentList = _uiState.value.selectedDrinks.toMutableList()
        if (currentList.contains(drink)) {
            currentList.remove(drink)
        } else {
            currentList.add(drink)
        }
        _uiState.value = _uiState.value.copy(selectedDrinks = currentList)
    }
}
