package com.orderize.orderize.ui.common.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.R
import com.orderize.orderize.ui.navigation.HistoryRoute
import com.orderize.orderize.ui.navigation.HomePizzaioloRoute
import com.orderize.orderize.ui.navigation.PizzaioloProfileRoute
import com.orderize.orderize.ui.theme.orderizeGray

@Composable
fun BottomNavBar(
    navController: NavController,
    loginType: Int? = null,
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val screensWithBottomNavPizzaiolo = listOf(
        HomePizzaioloRoute::class.qualifiedName!!,
        HistoryRoute::class.qualifiedName!!,
        PizzaioloProfileRoute::class.qualifiedName!!
    )
    val screensWithBottomNavAttendant = emptyList<String>()


    val shouldShowBottomNavPizzaiolo = screensWithBottomNavPizzaiolo.any { route ->
        currentRoute?.startsWith(route) == true
    }
    if (shouldShowBottomNavPizzaiolo) {
        BottomAppBar(
            containerColor = orderizeGray,
            actions = {
                NavigationBarItem(
                    selected = currentRoute?.startsWith(HomePizzaioloRoute::class.qualifiedName!!) ?: false,
                    onClick = {
                        val isRoute = currentRoute?.startsWith(HomePizzaioloRoute::class.qualifiedName!!) ?: false
                        if (!isRoute)
                            navController.navigate(HomePizzaioloRoute)
                    },
                    icon = {
                        val isRoute = currentRoute?.startsWith(HomePizzaioloRoute::class.qualifiedName!!) ?: false
                        if (!isRoute) {
                            Icon(
                                painterResource(R.drawable.ic_pizza_default),
                                contentDescription = "Ícone de Pizza não selecionado",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(34.dp)
                            )
                        } else {
                            Icon(
                                painterResource(R.drawable.ic_pizza_red),
                                contentDescription = "Ícone de Pizza selecionado",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(34.dp)
                            )
                        }
                    },
                    label = {
                        val isRoute = currentRoute?.startsWith(HomePizzaioloRoute::class.qualifiedName!!) ?: false
                        if (!isRoute) {
                            Text("Pedidos", color = Color.Black)
                        } else {
                            Text("Pedidos", color = Color.Red)
                        }
                    }
                )
                NavigationBarItem(
                    selected = currentRoute?.startsWith(HistoryRoute::class.qualifiedName!!) ?: false,
                    onClick = {
                        val isRoute = currentRoute?.startsWith(HistoryRoute::class.qualifiedName!!) ?: false
                        if (!isRoute)
                            navController.navigate(HistoryRoute)
                    },
                    icon = {
                        val isRoute = currentRoute?.startsWith(HistoryRoute::class.qualifiedName!!) ?: false
                        if (!isRoute) {
                            Icon(
                                painterResource(R.drawable.ic_history_default),
                                contentDescription = "Ícone de Histórico não selecionado",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(34.dp)
                            )
                        } else {
                            Icon(
                                painterResource(R.drawable.ic_history_red),
                                contentDescription = "Ícone de Histórico selecionado",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(34.dp)
                            )
                        }
                           },
                    label = {
                        val isRoute = currentRoute?.startsWith(HistoryRoute::class.qualifiedName!!) ?: false
                        if (!isRoute) {
                            Text("Histórico", color = Color.Black)
                        } else {
                            Text("Histórico", color = Color.Red)
                        }
                    }
                )
                NavigationBarItem(
                    selected = currentRoute?.startsWith(PizzaioloProfileRoute::class.qualifiedName!!) ?: false,
                    onClick = {
                        val isRoute = currentRoute?.startsWith(PizzaioloProfileRoute::class.qualifiedName!!) ?: false
                        if (!isRoute)
                            navController.navigate(PizzaioloProfileRoute)
                    },
                    icon = {
                        val isRoute = currentRoute?.startsWith(PizzaioloProfileRoute::class.qualifiedName!!) ?: false
                        if (!isRoute) {
                            Icon(
                                painterResource(R.drawable.ic_profile_default),
                                contentDescription = "Ícone de Perfil não selecionado",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(34.dp)
                            )
                        } else {
                            Icon(
                                painterResource(R.drawable.ic_profile_red),
                                contentDescription = "Ícone de Perfil selecionado",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(34.dp)
                            )
                        }
                           },
                    label = {
                        val isRoute = currentRoute?.startsWith(PizzaioloProfileRoute::class.qualifiedName!!) ?: false
                        if (!isRoute) {
                            Text("Perfil", color = Color.Black)
                        } else {
                            Text("Perfil", color = Color.Red)
                        }
                    }
                )

            }
        )
    } else if (currentRoute in screensWithBottomNavAttendant) {
        // TODO: Telas do atendente com bottomAppBar
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavBarPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    BottomNavBar(
        navController,
        null
    )
}
