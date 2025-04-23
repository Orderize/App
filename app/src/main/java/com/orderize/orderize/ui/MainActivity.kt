package com.orderize.orderize.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.orderize.orderize.ui.navigation.OrderizeNavigation
import com.orderize.orderize.ui.theme.OrderizeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OrderizeTheme {
                OrderizeNavigation()
            }
        }
    }
}