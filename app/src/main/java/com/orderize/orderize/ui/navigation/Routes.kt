package com.orderize.orderize.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

@Serializable
object ForgetPasswordRoute

@Serializable
object HomePizzaioloRoute

@Serializable
object HistoryRoute

@Serializable
object WriteOrderRoute

@Serializable
data class OrderDetailsRoute(
    val showStatus: Boolean = true,
    val order: String
)

@Serializable
object OrderCreateRoute

@Serializable
object PizzaioloProfileRoute

@Serializable
object DrinkRoute
