package com.orderize.orderize.ui.heatmap.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FiltersRow() {
    var selectedFilter by remember { mutableStateOf("salao") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DropdownRoomsFilters(
            isSelected = selectedFilter == "salao",
            onClick = { selectedFilter = "salao"},
            modifier = Modifier.weight(1f))

        StatusButton(
            isSelected = selectedFilter == "status",
            onClick = {selectedFilter = "status"},
            modifier = Modifier.weight(1f))
    }
}