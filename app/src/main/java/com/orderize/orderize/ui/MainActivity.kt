package com.orderize.orderize.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
//import com.orderize.orderize.model.MockTable
import com.orderize.orderize.ui.drinks.DrinkScreen
import com.orderize.orderize.ui.drinks.DrinkViewModel
import com.orderize.orderize.ui.navigation.OrderizeNavigation
import com.orderize.orderize.ui.theme.OrderizeTheme
//import com.orderize.orderize.ui.heatmap.HeatmapScreen
//import com.orderize.orderize.ui.heatmap.HeatmapScreenUiState
//import com.orderize.orderize.ui.heatmap.HeatmapViewModel
//import com.orderize.orderize.ui.heatmap.mockTables
import com.orderize.orderize.ui.theme.OrderizeTheme
import com.orderize.orderize.ui.writeOrder.WriteOrderScreen
import com.orderize.orderize.ui.writeOrder.writeOrderViewModel

//import com.orderize.orderize.ui.writeOrder.WriteOrderScreen
//import com.orderize.orderize.ui.writeOrder.writeOrderViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OrderizeTheme {
                val navController = rememberNavController()
                DrinkScreen(viewModel = DrinkViewModel(), navController = navController)
                //HeatmapScreen(state = HeatmapScreenUiState(mockTables), navController = navController)
                //WriteOrderScreen(viewModel = writeOrderViewModel(), navController = navController)
                //OrderizeNavigation()
            }
        }
    }
}