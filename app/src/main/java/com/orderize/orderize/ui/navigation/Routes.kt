package com.orderize.orderize.ui.navigation

sealed class Routes(val route: String) {
    data object Login: Routes("login")
    data object HomePizzaiolo: Routes("home-pizzaiolo")
}