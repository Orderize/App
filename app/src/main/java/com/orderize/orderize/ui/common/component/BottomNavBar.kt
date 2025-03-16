package com.orderize.orderize.ui.common.component

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.R
import com.orderize.orderize.ui.login.LoginScreen
import com.orderize.orderize.ui.navigation.Routes
import com.orderize.orderize.ui.theme.mossGreen
import com.orderize.orderize.ui.theme.orderizeGray

@Composable
fun BottomNavBar(
    navController: NavController,
    loginType: Int?,
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Log.i("BottomNavBar", "rota atual $currentRoute")
    val screensWithBottomNavPizzaiolo = listOf(Routes.HomePizzaiolo.route)
    val screensWithBottomNavAttendant = emptyList<String>()

    if (currentRoute in screensWithBottomNavPizzaiolo) {
        BottomAppBar(
            containerColor = orderizeGray,
            actions = {
                NavigationBarItem(
                    selected = currentRoute == Routes.HomePizzaiolo.route,
                    onClick = {
                        if (currentRoute != Routes.HomePizzaiolo.route)
                            navController.navigate(Routes.HomePizzaiolo.route)
                    },
                    icon = {
                        if (currentRoute != Routes.HomePizzaiolo.route) {
                            Icon(
                                painterResource(R.drawable.ic_pizza_default),
                                contentDescription = "Ícone de Pizza não selecionado",
                                tint = Color.Unspecified
                            )
                        } else {
                            Icon(
                                painterResource(R.drawable.ic_pizza_red),
                                contentDescription = "Ícone de Pizza selecionado",
                                tint = Color.Unspecified
                            )
                        }
                    },
                    label = {
                        if (currentRoute != Routes.HomePizzaiolo.route) {
                            Text("Pedidos", color = Color.Black)
                        } else {
                            Text("Pedidos", color = Color.Red)
                        }
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(
                        painterResource(R.drawable.ic_history_default),
                        contentDescription = "Ícone de Histórico não selecionado",
                        tint = Color.Unspecified
                    ) },
                    label = { Text("Histórico", color = Color.Black) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(
                        painterResource(R.drawable.ic_profile_default),
                        contentDescription = "Ícone de Perfil não selecionado",
                        tint = Color.Unspecified
                    ) },
                    label = { Text("Perfil", color = Color.Black) }
                )

                NavigationBarItem(
                    selected = currentRoute == Routes.Historico.route,
                    onClick = {
                        if (currentRoute != Routes.Historico.route)
                            navController.navigate(Routes.Historico.route)
                    },
                    icon = {
                        if (currentRoute != Routes.Historico.route) {
                            Icon(
                                painterResource(R.drawable.ic_history_default),
                                contentDescription = "Ícone de Histórico não selecionado",
                                tint = Color.Unspecified
                            )
                        } else {
                            Icon(
                                painterResource(R.drawable.ic_history_red),
                                contentDescription = "Ícone de Histórico selecionado",
                                tint = Color.Unspecified
                            )
                        }
                    },
                    label = {
                        Text(
                            "Histórico",
                            color = if (currentRoute == Routes.Historico.route) Color.Red else Color.Black
                        )
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
