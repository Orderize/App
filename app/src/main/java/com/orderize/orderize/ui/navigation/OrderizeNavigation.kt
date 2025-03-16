package com.orderize.orderize.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.ui.common.component.BottomNavBar
import com.orderize.orderize.ui.history.ordersHistory
import com.orderize.orderize.ui.login.LoginScreen
import com.orderize.orderize.ui.login.LoginViewModel
import com.orderize.orderize.ui.pizzaiolo_home.PizzaioloHomeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderizeNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController, null)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Routes.Login.route, builder = {
            composable(Routes.Login.route) {
                val viewModel: LoginViewModel = koinViewModel()
                LoginScreen(viewModel = viewModel, modifier = Modifier.padding(innerPadding), navController = navController)
            }
            composable(
                Routes.HomePizzaiolo.route,
            ) {
                PizzaioloHomeScreen(navController = navController, modifier = Modifier.padding(innerPadding))
            }

            composable(Routes.Historico.route) {
                ordersHistory(navController = navController, modifier = Modifier.padding(innerPadding))
            }
        })
    }
}