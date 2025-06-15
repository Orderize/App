package com.orderize.orderize.ui.navigation

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.orderize.orderize.model.Order
import com.orderize.orderize.ui.common.SharedOrderViewModel
import com.orderize.orderize.ui.common.component.BottomNavBar
import com.orderize.orderize.ui.common.component.TopBar
import com.orderize.orderize.ui.drinks.DrinkScreen
import com.orderize.orderize.ui.drinks.DrinkViewModel
import com.orderize.orderize.ui.forgotpassword.ForgotPasswordViewModel
import com.orderize.orderize.ui.gemini.GeminiViewModel
import com.orderize.orderize.ui.history.HistoryOrdersScreen
import com.orderize.orderize.ui.history.HistoryViewModel
import com.orderize.orderize.ui.login.ForgotPasswordScreen
import com.orderize.orderize.ui.login.LoginScreen
import com.orderize.orderize.ui.login.LoginViewModel
import com.orderize.orderize.ui.order.OrderScreen
import com.orderize.orderize.ui.order.OrderViewModel
import com.orderize.orderize.ui.orderdetails.OrderDetailsScreen
import com.orderize.orderize.ui.orderdetails.OrderDetailsViewModel
import com.orderize.orderize.ui.pizzaiolo_home.PizzaioloHomeScreen
import com.orderize.orderize.ui.pizzaiolo_home.PizzaioloHomeViewModel
import com.orderize.orderize.ui.profile.ProfileScreen
import com.orderize.orderize.ui.profile.ProfileViewModel
import com.orderize.orderize.ui.writeOrder.WriteOrderScreen
import com.orderize.orderize.ui.writeOrder.WriteOrderViewModel
import com.orderize.orderize.util.LocalDateAdapter
import com.orderize.orderize.util.LocalTimeAdapter
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.LocalTime

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
        val modifier = Modifier.padding(innerPadding)
        NavHost (navController = navController, startDestination = LoginRoute) {
            composable<LoginRoute> {
                val viewModel: LoginViewModel = koinViewModel()
                LoginScreen(viewModel = viewModel, modifier = modifier, navController = navController)
            }

            composable<HomePizzaioloRoute> {
                val viewModel: PizzaioloHomeViewModel = koinViewModel()
                PizzaioloHomeScreen(viewModel = viewModel, navController = navController, modifier = modifier)
            }

            composable<OrderDetailsRoute> { navBackStackEntry ->
                val arguments = navBackStackEntry.toRoute<OrderDetailsRoute>()
                val viewModel: OrderDetailsViewModel = koinViewModel()
                val decodedJson = Uri.decode(arguments.order)
                val gson = GsonBuilder()
                    .registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
                    .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
                    .create()
                val order = gson.fromJson(decodedJson, Order::class.java)
                OrderDetailsScreen(
                    viewModel = viewModel,
                    navController = navController,
                    modifier = modifier,
                    showStatus = arguments.showStatus,
                    order = order
                )
            }

            composable<WriteOrderRoute> {
                val geminiViewModel: GeminiViewModel = koinViewModel()
                val viewModel: WriteOrderViewModel = koinViewModel()
                WriteOrderScreen(
                    geminiViewModel = geminiViewModel,
                    viewModel = viewModel,
                    navController = navController
                )
            }

            composable<HistoryRoute> {
                HistoryOrdersScreen(navController = navController, modifier = modifier, viewModel = koinViewModel())
            }

            composable<ForgotPasswordRoute> {
                val viewModel: ForgotPasswordViewModel = koinViewModel()

                ForgotPasswordScreen(viewModel = viewModel, navController = navController, modifier = modifier)
            }

            composable<PizzaioloProfileRoute> {
                val viewModel: ProfileViewModel = koinViewModel()
                ProfileScreen(
                    viewModel = viewModel,
                    modifier = modifier,
                    navController = navController
                )
            }

            composable<OrderCreateRoute> {
                val orderViewModel: OrderViewModel = koinViewModel()
                val drinkViewModel: DrinkViewModel = koinViewModel()
                val geminiViewModel: GeminiViewModel = koinViewModel()
                val writeOrderViewModel: WriteOrderViewModel = koinViewModel()

                OrderScreen(
                    orderViewModel = orderViewModel,
                    drinkViewModel = drinkViewModel,
                    geminiViewModel = geminiViewModel,
                    writeOrderViewModel = writeOrderViewModel,
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            composable<DrinkRoute>{
                val viewModel: DrinkViewModel = koinViewModel()
                DrinkScreen(navController = navController,viewModel = viewModel, modifier = modifier)
            }
        }
    }
}