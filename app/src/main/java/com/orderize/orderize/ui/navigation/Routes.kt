package com.orderize.orderize.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

@Serializable
object ForgotPasswordRoute

@Serializable
object HomePizzaioloRoute

@Serializable
object HistoryRoute

@Serializable
object WriteOrderRoute

@Serializable
data class OrderDetailsRoute(
    val itemId: Long,
    val showStatus: Boolean = true
)

@Serializable
object OrderCreateRoute

@Serializable
object PizzaioloProfileRoute

@Serializable
object DrinkRoute