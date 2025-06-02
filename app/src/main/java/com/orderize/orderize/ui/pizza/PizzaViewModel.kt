package com.orderize.orderize.ui.pizza

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class PizzaViewModel: ViewModel() {
    private val _uiState = mutableStateOf(PizzaUiState())
    val uiState: State<PizzaUiState> = _uiState

    fun selecionarMassa(massa: String) {
        _uiState.value = _uiState.value.copy(massaSelecionada = massa)
    }

    fun selecionarBorda(borda: String) {
        _uiState.value = _uiState.value.copy(bordaSelecionada = borda)
    }

    val descricaoSabores = mutableStateOf("1 sabor: Calabresa (sem cebola)")

    fun atualizarDescricao(novaDescricao: String) {
        descricaoSabores.value = novaDescricao
    }
}