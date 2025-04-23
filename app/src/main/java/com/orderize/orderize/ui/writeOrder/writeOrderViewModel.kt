package com.orderize.orderize.ui.writeOrder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class writeOrderViewModel : ViewModel() {
    var uiState by mutableStateOf(writeOrderUiState())
        private set

    fun onTextChange(newText: String) {
        uiState = uiState.copy(text = newText)
    }
}