package com.orderize.orderize.ui.pizzaiolo_home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.ui.common.component.OrderCard
import com.orderize.orderize.ui.navigation.Routes
import com.orderize.orderize.ui.theme.backgroundGreen
import com.orderize.orderize.ui.theme.mossGreen

@Composable
fun PizzaioloHomeScreen(
    viewModel: PizzaioloHomeViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    PizzaioloHomeScreen(
        state,
        navController,
        modifier
    )
}

@Composable
fun PizzaioloHomeScreen(
    state: PizzaioloScreenUiState = PizzaioloScreenUiState(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundGreen)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(state.items) {
            OrderCard(
                item = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                onCardClick = {
                    navController.navigate(Routes.OrderDetails.createRoute(itemId = it.id))
                }
            )
        }
    }
}

@Preview
@Composable
private fun PizzaioloHomeScreenPreview() {
    val navController = rememberNavController()
    PizzaioloHomeScreen(
        PizzaioloScreenUiState(mockOrders),
        navController = navController
    )
}