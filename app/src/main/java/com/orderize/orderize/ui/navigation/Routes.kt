package com.orderize.orderize.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

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