package com.orderize.orderize.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.repository.AppDataStore
import com.orderize.orderize.repository.GeminiRepository
import com.orderize.orderize.repository.drink.DrinkRepository
import com.orderize.orderize.repository.drink.network.DrinkService
import com.orderize.orderize.repository.drink.network.IDrinkService
import com.orderize.orderize.ui.drinks.DrinkScreen
import com.orderize.orderize.ui.drinks.DrinkViewModel
import com.orderize.orderize.ui.gemini.GeminiViewModel
import com.orderize.orderize.ui.navigation.OrderizeNavigation
import com.orderize.orderize.ui.theme.OrderizeTheme
import com.orderize.orderize.ui.writeOrder.WriteOrderScreen
import com.orderize.orderize.ui.writeOrder.WriteOrderViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val drinkService: DrinkService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStore = AppDataStore(this)

        val drinkRepository = DrinkRepository(drinkService, dataStore)


        setContent {
            OrderizeTheme {
//                WriteOrderScreen(
//                    geminiViewModel = GeminiViewModel(GeminiRepository()),
//                    viewModel = WriteOrderViewModel(),
//                    navController = rememberNavController()
//                )
                val navController = rememberNavController()
                val drinkViewModel = DrinkViewModel(drinkRepository)

                DrinkScreen(
                    navController = navController,
                    viewModel = drinkViewModel
                )
            }
        }
    }
}

//package com.orderize.orderize.ui
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import com.orderize.orderize.ui.navigation.OrderizeNavigation
//import com.orderize.orderize.ui.theme.OrderizeTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            OrderizeTheme {
//                OrderizeNavigation()
//            }
//        }
//    }
//}