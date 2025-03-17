package com.orderize.orderize.ui.navigation

sealed class Routes(val route: String) {
    data object Login: Routes("login")
    data object HomePizzaiolo: Routes("home-pizzaiolo")
    data object History : Routes("ordersHistory")
    data object OrderDetails: Routes("order-details/{itemId}") {
        fun createRoute(itemId: Long): String {
            return "order-details/$itemId"
        }
    }
}