package com.orderize.orderize.model

import androidx.compose.ui.graphics.Color
import com.orderize.orderize.ui.theme.pendingRed
import com.orderize.orderize.ui.theme.preparingYellow
import java.time.LocalDate
import java.time.LocalTime

data class Order(
    val id: Long,
    val client: User,
    val pizzas: List<Pizza>,
    val drinks: List<Drink>,
    val date: LocalTime,
    val type: String,
    val price: Double,
    val table: Long,
    val status: String,
    val lastModified: LocalTime,
    val creationDate: LocalDate = LocalDate.now()
) {

    fun isSallon() = type == "saloon"
    fun statusColor() = when (status) {
        "PENDENTE" -> pendingRed
        "EM PREPARO" -> preparingYellow
        else -> Color.Green
    }
}
