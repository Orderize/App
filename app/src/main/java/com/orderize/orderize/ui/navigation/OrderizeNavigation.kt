package com.orderize.orderize.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.orderize.orderize.ui.common.component.BottomNavBar
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
        })
    }
}