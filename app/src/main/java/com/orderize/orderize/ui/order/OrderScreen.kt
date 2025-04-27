package com.orderize.orderize.ui.order

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.ui.common.component.Button
import com.orderize.orderize.ui.common.component.ButtonProgress
import com.orderize.orderize.ui.login.LoginScreen
import com.orderize.orderize.ui.order.component.CardItem
import com.orderize.orderize.R
import com.orderize.orderize.ui.order.component.CardClient
import com.orderize.orderize.ui.order.component.CardTotal
import com.orderize.orderize.ui.theme.fundo

@Composable
fun OrderScreen(
    viewModel: OrderViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    OrderScreen(
        state,
        navController,
        modifier
    )
}

@Composable
fun OrderScreen(
    state: OrderScreenUiState = OrderScreenUiState(),
    controller: NavController,
    modifier: Modifier
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
                    if(isOpen) cardDrink = false
                    else cardDrink = true
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

@Preview(showSystemUi = true)
@Composable
private fun OrderScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val mockViewModel = OrderViewModel()
    OrderScreen(viewModel = mockViewModel, navController = navController, modifier = modifier)
}


