package com.orderize.orderize.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orderize.orderize.R
import com.orderize.orderize.model.Drink
import com.orderize.orderize.model.Flavor
import com.orderize.orderize.model.Order
import com.orderize.orderize.model.Pizza
import com.orderize.orderize.model.User
import com.orderize.orderize.model.enum.OrderStatus
import com.orderize.orderize.ui.theme.strokeGray
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun OrderCard(
    item: Order,
    modifier: Modifier = Modifier,
    onCardClick: (Order) -> Unit = {},
    showStatus: Boolean = true
) {
    Card(
        modifier = modifier
            .border(1.dp, strokeGray, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = { onCardClick(item) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = "Pedido nº ${item.id}",
                    color = Color.Black,
                    fontSize = 24.sp,
                )

                Row(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .offset(x = -12.dp),
                        painter = painterResource(R.drawable.ic_gray_clock),
                        contentDescription = "Ícone de relógio"
                    )
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .offset(x = -22.dp, y = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.date.format(DateTimeFormatter.ofPattern("HH:mm")),
                            color = strokeGray,
                            fontSize = 24.sp,
                        )

                        if (!showStatus) {
                            Image(
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(horizontal = 4.dp),
                                painter = painterResource(R.drawable.calendar),
                                contentDescription = "Ícone de calendário"
                            )

                            Text(
                                text = item.creationDate.format(DateTimeFormatter.ofPattern("dd/MM")),
                                color = strokeGray,
                                fontSize = 24.sp,
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showStatus) {
                    val status = when (item.status) {
                        OrderStatus.PENDENTE.databaseName -> OrderStatus.PENDENTE.displayName
                        OrderStatus.EM_PREPARO.databaseName -> OrderStatus.EM_PREPARO.displayName
                        else -> OrderStatus.FINALIZADO.displayName
                    }
                    Text(
                        text = status,
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                color = item.statusColor(),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(vertical = 6.dp, horizontal = 12.dp)
                    )
                }

                if (item.isSallon()) {
                    Image(
                        modifier = Modifier
                            .size(60.dp),
                        painter = painterResource(R.drawable.ic_table),
                        contentDescription = "Ícone de pedido salão"
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .size(60.dp),
                        painter = painterResource(R.drawable.ic_motorcycle),
                        contentDescription = "Ícone de pedido delivery"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderCardPreview(modifier: Modifier = Modifier) {
    val mockOrder = Order(
        id = 1L,
        client = User(
            id = 101L,
            name = "Maria Oliveira",
            email = "maria.oliveira@email.com",
            role = "",
            companyId = 1L
        ),
        pizzas = listOf(
            Pizza(
                id = 201L,
                name = "Meia Calabresa Meia Portuguesa",
                price = 55.00,
                observations = "Sem cebola na portuguesa",
                flavors = listOf(
                    Flavor(
                        id = 301L,
                        name = "Calabresa",
                        description = "Calabresa fatiada com cebola e orégano",
                        price = 0.0
                    ),
                    Flavor(
                        id = 302L,
                        name = "Portuguesa",
                        description = "Presunto, queijo, ovos, cebola, pimentão e azeitonas",
                        price = 0.0
                    )
                ),
                border = "Catupiry",
                size = "Grande",
                mass = "Tradicional"
            )
        ),
        drinks = listOf(
            Drink(
                id = 401L,
                name = "Coca-Cola",
                description = "Refrigerante de Cola",
                price = 8.00,
                milimeters = 600
            ),
            Drink(
                id = 402L,
                name = "Suco de Laranja",
                description = "Suco natural de laranja",
                price = 7.00,
                milimeters = 500
            )
        ),
        date = LocalTime.now(),
        type = "Delivery",
        price = 55.00,
        table = 0L,
        status = "PENDENTE",
        lastModified = LocalTime.now()
    )
    OrderCard(mockOrder)
}