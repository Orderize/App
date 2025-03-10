package com.orderize.orderize.ui.pizzaiolo_home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.orderize.orderize.ui.theme.backgroundGreen
import com.orderize.orderize.ui.theme.mossGreen

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
    ) {

    }
}