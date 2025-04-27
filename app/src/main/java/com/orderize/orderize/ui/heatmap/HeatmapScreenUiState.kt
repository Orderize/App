package com.orderize.orderize.ui.heatmap

import com.orderize.orderize.model.MockTable

data class HeatmapScreenUiState(
    val tables: List<MockTable> = emptyList()
)
