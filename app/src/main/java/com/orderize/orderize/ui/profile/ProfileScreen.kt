package com.orderize.orderize.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.orderize.orderize.R
import com.orderize.orderize.ui.navigation.LoginRoute
import com.orderize.orderize.ui.theme.backgroundGreen
import com.orderize.orderize.ui.theme.darkerMossGreen

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()
    ProfileScreen(
        state = state,
        modifier = modifier,
        navController = navController
    )
}

@Composable
fun ProfileScreen(
    state: ProfileScreenUiState,
    modifier: Modifier = Modifier,
    navController: NavController
) {

    LaunchedEffect(state.userDisconnected) {
        if (state.userDisconnected) {
            navController.navigate(LoginRoute) {
                popUpTo(LoginRoute) { inclusive = false }
                launchSingleTop = true
            }
        }
    }
    LaunchedEffect(Unit) {
        state.getOrdersData()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundGreen)
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 8.dp, vertical = 14.dp)
                .height(54.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Qtd Pedidos concluídos hoje",
                        color = Color.Black,
                        fontSize = 14.sp
                    )

                    Text(
                        text = state.finishedOrders.toString(),
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }
            }

        }

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 8.dp, vertical = 14.dp)
                .height(54.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Qtd Pedidos pendentes de hoje",
                        color = Color.Black,
                        fontSize = 14.sp
                    )

                    Text(
                        text = state.pendingOrders.toString(),
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }
            }

        }

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 8.dp, vertical = 14.dp)
                .height(54.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Qtd Pedidos em preparação de hoje",
                        color = Color.Black,
                        fontSize = 14.sp
                    )

                    Text(
                        text = state.preparingOrders.toString(),
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }
            }

        }

        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_speak_baloon),
                contentDescription = "Imagem de fundo",
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(38.dp)
            ) {
                Text(
                    text = "Nome:",
                    color = Color.White,
                    fontSize = 18.sp
                )

                Text(
                    text = state.name,
                    color = Color.White,
                    fontSize = 18.sp
                )

                Spacer(Modifier.size(16.dp))

                Text(
                    text = "Função:",
                    color = Color.White,
                    fontSize = 18.sp
                )

                Text(
                    text = state.role,
                    color = Color.White,
                    fontSize = 18.sp
                )

                Spacer(Modifier.size(16.dp))

                Text(
                    text = "Pizzaria:",
                    color = Color.White,
                    fontSize = 18.sp
                )

                Text(
                    text = state.companyName,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }

        Spacer(Modifier.size(16.dp))

        Button(
            onClick = state.logout,
            colors = ButtonDefaults.buttonColors(
                darkerMossGreen
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(46.dp)
                .width(128.dp)

        ) {
            Text(
                text = "Sair",
                color = Color.White,
                fontSize = 22.sp
            )
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//private fun ProfileScreenPreview() {
//    ProfileScreen(
//        state = ProfileScreenUiState()
//    )
//}