package com.orderize.orderize.ui.heatmap
import com.orderize.orderize.model.Table

data class HeatmapScreenUiState(
   val tables: List<Table> = emptyList(),
   val room: String = "",
   val onRoomChange: (String) -> Unit = {},
   val statusFilter: String = "",
   val onStatusFilterChange: (String) -> Unit = {},
   val onTableClick: (Table) -> Unit = {},
   val isLoading: Boolean = false,
   val isEmpty: Boolean = false
)
