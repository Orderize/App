package com.orderize.orderize.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.orderize.orderize.repository.GeminiRepository
import com.orderize.orderize.ui.common.component.BottomNavBar
import com.orderize.orderize.ui.common.component.TopBar
import com.orderize.orderize.ui.gemini.GeminiViewModel
import com.orderize.orderize.ui.history.HistoryOrdersScreen
import com.orderize.orderize.ui.history.HistoryViewModel
import com.orderize.orderize.ui.forgotpassword.ForgotPasswordViewModel
import com.orderize.orderize.ui.login.ForgotPasswordScreen
import com.orderize.orderize.ui.login.LoginScreen
import com.orderize.orderize.ui.login.LoginViewModel
import com.orderize.orderize.ui.order.OrderScreen
import com.orderize.orderize.ui.order.OrderViewModel
import com.orderize.orderize.ui.orderdetails.OrderDetailsScreen
import com.orderize.orderize.ui.orderdetails.OrderDetailsViewModel
import com.orderize.orderize.ui.pizzaiolo_home.PizzaioloHomeScreen
import com.orderize.orderize.ui.pizzaiolo_home.PizzaioloHomeViewModel
import com.orderize.orderize.ui.writeOrder.WriteOrderScreen
import com.orderize.orderize.ui.writeOrder.WriteOrderViewModel
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
                    modifier = Modifier.padding(innerPadding),
                    showStatus = arguments.showStatus
                )
            }

            composable<WriteOrderRoute> {
                val geminiViewModel: GeminiViewModel = GeminiViewModel(GeminiRepository())
                val viewModel: WriteOrderViewModel = koinViewModel()
                WriteOrderScreen(
                    geminiViewModel = geminiViewModel,
                    viewModel = viewModel,
                    navController = navController
                )
            }

            composable<HistoryRoute> {
                HistoryOrdersScreen(navController = navController, modifier = Modifier.padding(innerPadding), viewModel = HistoryViewModel())
            }

            composable<ForgotPasswordRoute> {
                val viewModel: ForgotPasswordViewModel = koinViewModel()

                ForgotPasswordScreen(viewModel = viewModel, navController = navController, modifier = Modifier.padding(innerPadding))
            }

            composable<OrderCreateRoute>{
                val viewModel: OrderViewModel = koinViewModel()
                OrderScreen(viewModel = viewModel, modifier = Modifier.padding(innerPadding), navController = navController)
            }
        }
    }
}