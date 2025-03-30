package com.orderize.orderize.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.orderize.orderize.ui.common.component.BottomNavBar
import com.orderize.orderize.ui.common.component.TopBar
import com.orderize.orderize.ui.forgotpassword.ForgotPasswordViewModel
import com.orderize.orderize.ui.history.History
import com.orderize.orderize.ui.login.ForgotPasswordScreen
import com.orderize.orderize.ui.login.LoginScreen
import com.orderize.orderize.ui.login.LoginViewModel
import com.orderize.orderize.ui.orderdetails.OrderDetailsScreen
import com.orderize.orderize.ui.orderdetails.OrderDetailsViewModel
import com.orderize.orderize.ui.pizzaiolo_home.PizzaioloHomeScreen
import com.orderize.orderize.ui.pizzaiolo_home.PizzaioloHomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderizeNavigation() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopBar(
                navController = navController
            )
        },
        bottomBar = {
            BottomNavBar(navController = navController, null)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost (navController = navController, startDestination = LoginRoute) {
            composable<LoginRoute> {
                val viewModel: LoginViewModel = koinViewModel()
                LoginScreen(viewModel = viewModel, modifier = Modifier.padding(innerPadding), navController = navController)
            }

            composable<HomePizzaioloRoute> {
                val viewModel: PizzaioloHomeViewModel = koinViewModel()
                PizzaioloHomeScreen(viewModel = viewModel, navController = navController, modifier = Modifier.padding(innerPadding))
            }

            composable<OrderDetailsRoute> { navBackStackEntry ->
                val arguments = navBackStackEntry.toRoute<OrderDetailsRoute>()
                val viewModel: OrderDetailsViewModel = koinViewModel()
                OrderDetailsScreen(
                    viewModel = viewModel,
                    itemId = arguments.itemId,
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            composable<HistoryRoute> {
                History(navController = navController, modifier = Modifier.padding(innerPadding))
            }

            composable<ForgotPasswordRoute> {
                val viewModel: ForgotPasswordViewModel = koinViewModel()

                ForgotPasswordScreen(viewModel = viewModel, navController = navController, modifier = Modifier.padding(innerPadding))
            }
        }
    }
}