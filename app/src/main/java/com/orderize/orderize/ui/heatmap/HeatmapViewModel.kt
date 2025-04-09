package com.orderize.orderize.ui.heatmap

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.orderize.orderize.model.MockTable
import com.orderize.orderize.model.TableStatus
import com.orderize.orderize.ui.theme.availableLightBeige
import com.orderize.orderize.ui.theme.pendingRed
import com.orderize.orderize.ui.theme.preparingYellow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HeatmapViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<HeatmapScreenUiState> = MutableStateFlow(
        HeatmapScreenUiState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                tables = mockTables
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

val mockTables = listOf(
    MockTable(
        1,
        1,
        TableStatus.PENDENTE
    ),
    MockTable(
        2,
        2,
        TableStatus.DISPONIVEL
    ),
    MockTable(
        3,
        3,
        TableStatus.EM_PREPARO
    ),
    MockTable(
        4,
        4,
        TableStatus.EM_PREPARO
    ),
    MockTable(
        5,
        5,
        TableStatus.DISPONIVEL
    ),
    MockTable(
        6,
        6,
        TableStatus.EM_PREPARO
    ),
    MockTable(
        7,
        7,
        TableStatus.EM_PREPARO
    ),
    MockTable(
        7,
        7,
        TableStatus.EM_PREPARO
    )
)