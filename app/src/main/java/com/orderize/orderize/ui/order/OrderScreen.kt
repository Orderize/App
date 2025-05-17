package com.orderize.orderize.ui.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.orderize.orderize.ui.common.component.ButtonProgress
import com.orderize.orderize.ui.drinks.DrinkViewModel
import com.orderize.orderize.ui.gemini.GeminiViewModel
import com.orderize.orderize.ui.navigation.DrinkRoute
import com.orderize.orderize.ui.order.component.CardItem
import com.orderize.orderize.ui.order.component.CardClient
import com.orderize.orderize.ui.order.component.CardTotal
import com.orderize.orderize.ui.theme.fundo
import com.orderize.orderize.ui.writeOrder.WriteOrderViewModel

@Composable
fun OrderScreen(
    orderViewModel: OrderViewModel,
    drinkViewModel: DrinkViewModel,
    geminiViewModel: GeminiViewModel,
    writeOrderViewModel: WriteOrderViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by orderViewModel.uiState.collectAsState()
    OrderScreen(
        state = state,
        navController = navController,
        orderViewModel = orderViewModel,
        drinkViewModel = drinkViewModel,
        geminiViewModel = geminiViewModel,
        writeOrderViewModel = writeOrderViewModel,
        modifier = modifier
    )
}

@Composable
fun OrderScreen(
    state: OrderScreenUiState = OrderScreenUiState(),
    navController: NavController,
    orderViewModel: OrderViewModel,
    drinkViewModel: DrinkViewModel,
    geminiViewModel: GeminiViewModel,
    writeOrderViewModel: WriteOrderViewModel,
    modifier: Modifier = Modifier
) {
    var cardPizza by remember { mutableStateOf(true) }
    var cardDrink by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(fundo),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .size(90.dp)
        ){
            CardClient()
        }

        Column (
            modifier = Modifier
                .padding(32.dp)
                .size(380.dp)
        ) {
            CardItem(
                titulo = "Pizzas",
                cardAberto = cardPizza,
                onCardAbertoChange = { isOpen ->
                    cardPizza = isOpen
                    if (isOpen) cardDrink = false
                    else cardDrink = true
                },
                onAddClick = {
                    navController.navigate(DrinkRoute)
                }
            )

            Spacer(Modifier.size(16.dp))

            CardItem(
                titulo = "Bebidas",
                cardAberto = cardDrink,
                onCardAbertoChange = { isOpen ->
                    cardDrink = isOpen
                    if(isOpen) cardPizza = false
                    else cardDrink = isOpen
                },
                onAddClick = {

                }
            )
        }

        Box(
            modifier = Modifier
                .padding(0.dp)
                .align(AbsoluteAlignment.Left)
        ){
            CardTotal()
        }

        Box(
            modifier = Modifier
                .size(200.dp)
        ){
            ButtonProgress("Finalizar Pedido")
        }
    }
}