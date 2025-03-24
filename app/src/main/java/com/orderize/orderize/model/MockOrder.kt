package com.orderize.orderize.model

import androidx.compose.ui.graphics.Color
import com.orderize.orderize.ui.theme.pendingRed
import com.orderize.orderize.ui.theme.preparingYellow
import java.time.LocalTime

data class MockOrder(
    val id: Long = 234,
    val type: String = "Salão",
    val status: String = "Pendente",
    val createdTime: LocalTime = LocalTime.of(12, 0),
    val items: List<MockPizza> = listOf(
        MockPizza(), MockPizza(
            qtt = 1,
            name = "Calabresa",
            observations = "Sem cebola"
        ), MockPizza(
            qtt = 3,
            name = "Calabria",
            observations = "Adicional de Azeitona"
        )
    )
) {
    fun isSallon() = type == "Salão"
    fun statusColor() = when (status) {
        "Pendente" -> pendingRed
        "Em Preparo" -> preparingYellow
        else -> Color.Green
    }
}
