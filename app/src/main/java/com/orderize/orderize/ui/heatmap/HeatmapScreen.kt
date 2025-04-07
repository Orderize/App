package com.orderize.orderize.ui.heatmap

import com.orderize.orderize.ui.heatmap.components.DropdownRoomsFilters
import com.orderize.orderize.ui.heatmap.components.StatusButton
import com.orderize.orderize.ui.heatmap.components.Legend
import com.orderize.orderize.ui.heatmap.components.CardTable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.ui.navigation.OrderDetailsRoute
import com.orderize.orderize.ui.theme.availableLightBeige
import com.orderize.orderize.ui.theme.backgroundGreen
import com.orderize.orderize.ui.theme.darkerMossGreen
import com.orderize.orderize.ui.theme.pendingRed
import com.orderize.orderize.ui.theme.preparingYellow
import com.orderize.orderize.ui.theme.textWhite

@Composable
fun HeatmapScreen(
    viewModel: HeatmapViewModel,
    navController: NavController,
    modifier: Modifier
) {
    val state by viewModel.uiState.collectAsState()
    HeatmapScreen(
        state,
        navController,
        modifier
    )
}


@Composable
fun HeatmapScreen(
    state: HeatmapScreenUiState = HeatmapScreenUiState(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = backgroundGreen
            )
            .padding(top = 34.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "Mapa de calor: mesas",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp),
            color = textWhite,
            fontWeight = FontWeight.SemiBold
        )

        FiltersRow()

        Card(
            colors = CardDefaults.cardColors(containerColor = darkerMossGreen),
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .padding(bottom = 16.dp)
        ) {
            Column(modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {

               Row(verticalAlignment = Alignment.CenterVertically) {
                   Text("Cores | ", color = textWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                   Legend("Pedido pendente", pendingRed)
               }
               Spacer(Modifier.height(8.dp))
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.spacedBy(12.dp),
               ) {
                   Legend("Pedido em preparo", preparingYellow)
                   Legend("Disponível", availableLightBeige)
               }
            }
        }

        Spacer(Modifier.width(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(state.tables) { table ->
                CardTable(table = table) {selectedTable  ->
                    navController.navigate(
                        OrderDetailsRoute(
                            itemId = table.id
                        )
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HeatmapScreenPreview() {
    val navController = rememberNavController()
    HeatmapScreen(
        HeatmapScreenUiState(mockTables),
        navController = navController
    )
}

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