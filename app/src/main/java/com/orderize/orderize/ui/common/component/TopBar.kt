package com.orderize.orderize.ui.common.component

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.orderize.orderize.ui.navigation.OrderDetailsRoute
import com.orderize.orderize.ui.theme.mossGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Log.i("TopBar", "rota atual $currentRoute")

    val screensWithTopAppBar = mapOf(
        OrderDetailsRoute::class.qualifiedName!! to "Detalhes",
    )

    val title = screensWithTopAppBar.keys.find { currentRoute?.startsWith(it) == true }
        ?.let { screensWithTopAppBar[it] }

    if (title != null) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }
            },
            modifier = modifier,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = mossGreen
            )
        )
    }
}
