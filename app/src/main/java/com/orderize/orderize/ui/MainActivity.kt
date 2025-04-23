package com.orderize.orderize.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.repository.GeminiRepository
import com.orderize.orderize.ui.gemini.GeminiViewModel
import com.orderize.orderize.ui.navigation.OrderizeNavigation
import com.orderize.orderize.ui.theme.OrderizeTheme
import com.orderize.orderize.ui.writeOrder.WriteOrderScreen
import com.orderize.orderize.ui.writeOrder.WriteOrderViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OrderizeTheme {
//                OrderizeNavigation()
                WriteOrderScreen(
                    geminiViewModel = GeminiViewModel(GeminiRepository()),
                    viewModel = WriteOrderViewModel(),
                    navController = rememberNavController()
                )
            }
        }
    }
}