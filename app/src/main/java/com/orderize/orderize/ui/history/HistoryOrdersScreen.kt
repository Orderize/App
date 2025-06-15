package com.orderize.orderize.ui.history

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.orderize.orderize.R
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.model.Order
import com.orderize.orderize.ui.common.component.OrderCard
import com.orderize.orderize.ui.navigation.OrderDetailsRoute
import com.orderize.orderize.ui.theme.backgroundGreen
import com.orderize.orderize.ui.theme.darkerMossGreen
import com.orderize.orderize.util.LocalDateAdapter
import com.orderize.orderize.util.LocalTimeAdapter
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun HistoryOrdersScreen(
    viewModel: HistoryViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getOrders()
    }

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
                        text = (state.items as NetResource.Fail).message,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        modifier = Modifier
                            .height(42.dp)
                            .width(200.dp),
                        onClick = { viewModel.getOrders() },
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
            val filteredItems =
                (state.items as NetResource.Success<List<Order>>).data.filter { order ->
                    order.id.toString().contains(state.searchQuery, ignoreCase = true)
                }
            Spacer(Modifier.height(10.dp))
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = backgroundGreen)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    Spacer(Modifier.height(10.dp))
                    SearchBar (
                        value = state.searchQuery,
                        onValueChange = { newValue -> viewModel.updateSearchQuery(newValue) }
                    )
                    Spacer(Modifier.height(10.dp))
                }

                items(filteredItems) { order ->
                    OrderCard(
                        item = order,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        onCardClick = {
                            val gson = GsonBuilder()
                                .registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
                                .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
                                .create()
                            val json = gson.toJson(it)
                            val encodedOrder = Uri.encode(json)
                            navController.navigate(
                                OrderDetailsRoute(
                                    order = encodedOrder,
                                    showStatus = false
                                )
                            )
                        },
                        showStatus = false
                    )
                }
            }
        }


    }
}

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Pesquise...") },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.iconlupa),
                contentDescription = "Ícone de busca"
            )
        },
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.LightGray,
            cursorColor = Color.LightGray
        )
    )
}
