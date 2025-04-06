package com.orderize.orderize.ui.heatmap

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orderize.orderize.model.TableStatus
import com.orderize.orderize.repository.heatmap.TableRepository
import com.orderize.orderize.ui.theme.availableLightBeige
import com.orderize.orderize.ui.theme.pendingRed
import com.orderize.orderize.ui.theme.preparingYellow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HeatmapViewModel (private val repository: TableRepository): ViewModel() {
    private val _uiState = MutableStateFlow(HeatmapScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            delay(300)
            repository.getTables().collect{ tables ->
                _uiState.update { currentState ->
                    currentState.copy(
                        tables = tables,
                        isEmpty = tables.isEmpty(),
                        isLoading = false
                    ) }
            }
        }

        _uiState.update {
            it.copy(
                onRoomChange = {room -> _uiState.update { u -> u.copy(room = room) }},
                onStatusFilterChange = {status -> _uiState.update { u -> u.copy(statusFilter = status) }}
            )
        }

    }
}

fun tableColorByStatus(status: TableStatus): Color{
    return when (status) {
        TableStatus.PENDENTE -> pendingRed
        TableStatus.EM_PREPARO -> preparingYellow
        TableStatus.DISPONIVEL -> availableLightBeige
    }
}

