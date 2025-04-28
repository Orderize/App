package com.orderize.orderize.ui.order.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.ui.drinks.DrinkViewModel
import com.orderize.orderize.ui.gemini.GeminiViewModel
import com.orderize.orderize.ui.order.OrderScreen
import com.orderize.orderize.ui.order.OrderViewModel
import com.orderize.orderize.ui.theme.backgroundGreen
import com.orderize.orderize.ui.theme.mossGreen
import com.orderize.orderize.ui.theme.orderizeGray
import com.orderize.orderize.ui.writeOrder.WriteOrderScreen
import com.orderize.orderize.ui.writeOrder.WriteOrderViewModel

@Composable
fun CardItem(
    titulo: String,
    cardAberto: Boolean,
    onCardAbertoChange: (Boolean) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = orderizeGray),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
//            .height(220.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.Bold,
                    color = mossGreen,
                    fontSize = 18.sp,
                    modifier = modifier
                        .clickable { onCardAbertoChange(!cardAberto) }
                )

                Button(
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),
                    onClick = onAddClick
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = backgroundGreen
                            )
                        )
                    }
                }
            }

            if (cardAberto) {
                Spacer(modifier = Modifier.height(12.dp))
//                conteudo():
                //aqui vem uma lazy column de detailsItem
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp)
                        .background(Color.White)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 150.dp)
                ) {
                    if(titulo == "Pizzas") {

                        PizzaItem(
                            onEditClick = { },
                            onDeleteClick = { }
                        )

                        PizzaItem(
                            onEditClick = { },
                            onDeleteClick = { }
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    if(titulo == "Bebidas"){
                        BebidaItem(
                            onDeleteClick = { }
                        )
                    }
                }
            }
        }
    }
}