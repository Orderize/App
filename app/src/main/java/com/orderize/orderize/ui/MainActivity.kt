package com.orderize.orderize.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.ui.navigation.OrderizeNavigation
import com.orderize.orderize.ui.pizza.PizzaScreen
import com.orderize.orderize.ui.pizza.PizzaViewModel
import com.orderize.orderize.ui.theme.OrderizeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            OrderizeTheme {
//                OrderizeNavigation()
//            }

            val navController = rememberNavController()
            val pizzaViewModel = PizzaViewModel()

            PizzaScreen(
                viewModel = pizzaViewModel,
                navController = navController
            )
        }
    }
}