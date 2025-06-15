package com.orderize.orderize.ui.pizzaiolo_home

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.ui.common.component.OrderCard
import com.orderize.orderize.ui.navigation.OrderDetailsRoute
import com.orderize.orderize.ui.theme.backgroundGreen
import com.orderize.orderize.ui.theme.darkerMossGreen
import com.orderize.orderize.ui.theme.orderizeGray
import com.orderize.orderize.util.LocalTimeAdapter
import java.time.LocalTime

@Composable
fun PizzaioloHomeScreen(
    viewModel: PizzaioloHomeViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getOrders()
    }
    PizzaioloHomeScreen(
        state,
        navController,
        modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaioloHomeScreen(
    state: PizzaioloScreenUiState = PizzaioloScreenUiState(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    when (state.items) {
        is NetResource.Processing -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = backgroundGreen),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Buscando pedidos...",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(26.dp),
                        color = Color.White
                    )
                }
            }
        }

        is NetResource.Fail -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = backgroundGreen),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.items.message,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        modifier = Modifier
                            .height(42.dp)
                            .width(200.dp),
                        onClick = { state.getOrders() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = darkerMossGreen
                        )
                    ) {
                        Text(
                            fontSize = 16.sp,
                            text = "Buscar Novamente",
                            color = Color.White
                        )
                    }
                }
            }
        }

        is NetResource.Success -> {
            PullToRefreshBox (
                isRefreshing = state.isRefreshing,
                onRefresh = { state.getOrders() },
                modifier = modifier
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = backgroundGreen)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(state.items.data) { order ->
                        OrderCard(
                            item = order,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            onCardClick = {
                                val gson = GsonBuilder()
                                    .registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
                                    .create()
                                val json = gson.toJson(order)
                                val encodedOrder = Uri.encode(json)
                                navController.navigate(
                                    OrderDetailsRoute(
                                        order = encodedOrder
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PizzaioloHomeScreenPreview() {
    val navController = rememberNavController()
    PizzaioloHomeScreen(
        PizzaioloScreenUiState(items = NetResource.Fail("Falha ao buscar os pedidos")),
        navController = navController
    )
}