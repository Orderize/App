package com.orderize.orderize.ui.heatmap

import androidx.compose.foundation.Image
import com.orderize.orderize.ui.heatmap.components.Legend
import com.orderize.orderize.ui.heatmap.components.CardTable
import com.orderize.orderize.ui.heatmap.components.FiltersRow


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.R
import com.orderize.orderize.ui.navigation.OrderDetailsRoute
import com.orderize.orderize.ui.theme.availableLightBeige
import com.orderize.orderize.ui.theme.backgroundGreen
import com.orderize.orderize.ui.theme.pendingRed
import com.orderize.orderize.ui.theme.preparingYellow
import com.orderize.orderize.ui.theme.textWhite

@Composable
fun HeatmapScreen(
    viewModel: HeatmapViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
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

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .offset(x = 16.dp),
            contentAlignment = Alignment.TopEnd) {
            Image(
                painter = painterResource(id = R.drawable.ballon_rigth),
                contentDescription = "Balão",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 18.dp),
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

        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }else{
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.run { spacedBy(30.dp) },
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                modifier = Modifier.weight(1f)
            ) {
                if (state.isEmpty) {
                    item(span = { GridItemSpan(2) }) {
                        Text(
                            text = "Nenhuma mesa encontrada.",
                            color = textWhite,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth().padding(top = 16.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }else{
                    items(state.tables) { table ->
                        CardTable(table = table) {it  ->
                            navController.navigate(
                                OrderDetailsRoute(
                                    itemId = it.id
                                )
                            )
                        }
                    }
                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HeatmapScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    HeatmapScreen(
        navController = navController,
        modifier = modifier
    )
}