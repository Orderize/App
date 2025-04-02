package com.orderize.orderize.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.R
import com.orderize.orderize.ui.common.component.OrderCard
import com.orderize.orderize.ui.navigation.OrderDetailsRoute
import com.orderize.orderize.ui.theme.backgroundGreen

@Composable
fun HistoryOrdersScreen(
    viewModel: HistoryViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()

    val filteredItems = state.items.filter { order ->
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
                    SearchBar(
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
                            navController.navigate(
                                OrderDetailsRoute(
                                    itemId = it.id
                                )
                            )
                        },
                        showStatus = false
                    )
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
            .height(56.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun HistoryScreenPreview() {
    val navController = rememberNavController()
    HistoryOrdersScreen(
        viewModel = HistoryViewModel(),
        navController = navController
    )
}
