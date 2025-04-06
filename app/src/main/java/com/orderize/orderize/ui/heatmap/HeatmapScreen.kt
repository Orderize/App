package com.orderize.orderize.ui.heatmap

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.model.MockTable
import com.orderize.orderize.model.TableStatus
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
            .padding(16.dp),
//        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Mapa de calor: mesas",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp),
            color = textWhite
//            fontSize = 24.sp,
//            fontWeight = FontWeight.SemiBold,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DropdownRoomsFilters()
            StatusButton()
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = darkerMossGreen),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Cores", color = textWhite)
                Spacer(Modifier.height(8.dp))
                Caption("Mesa com pedido pendente", pendingRed)
                Caption("Mesa com pedido em preparo", preparingYellow)
                Caption("Mesa disponível", availableLightBeige)
            }
        }

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
fun DropdownRoomsFilters() {
    Button(onClick = { }) {
        Text("Salão Principal")
    }
}

@Composable
fun StatusButton() {
    Button(onClick = { }) {
        Icon(Icons.Default.Menu, contentDescription = "Status")
        Spacer(modifier = Modifier.width(4.dp))
        Text("status")
    }
}

@Composable
fun Caption(texto: String, cor: Color) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ){
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(cor, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = texto, color = textWhite)
    }
}

@Composable
fun CardTable(table: MockTable, onClick: (MockTable) -> Unit = {}) {
    val clickable = table.status == TableStatus.PENDENTE || table.status == TableStatus.EM_PREPARO
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .then(
                if (clickable) Modifier.clickable { onClick(table) } else Modifier
            ),
        colors = CardDefaults.cardColors(containerColor = tableColorByStatus(table.status))
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Mesa ${table.number}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}