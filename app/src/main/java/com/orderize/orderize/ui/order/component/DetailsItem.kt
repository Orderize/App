package com.orderize.orderize.ui.order.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PizzaItem(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
    ){
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {

            Column(
                Modifier.padding(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "1. Pizza: R$",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2D2A20)
                    )

                    Row {
                        IconButton(onClick = onEditClick) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar",
                                tint = Color(0xFF2D2A20)
                            )
                        }
                        IconButton(onClick = onDeleteClick) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Remover",
                                tint = Color(0xFF2D2A20)
                            )
                        }
                    }
                }
            }

//            Spacer(modifier = Modifier.height(1.dp))

            Column (
                modifier = Modifier.padding(8.dp)
            ){
                Text(
                    text = "Sabor (código)",
                    color = Color(0xFF2D2A20),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                )

                Text(
                    text = "-observacao",
                    color = Color(0xFF97948F),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(vertical = 8.dp)
                )
            }

//            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview
    (showBackground = true)
@Composable
private fun PizzaItemPrev() {
    PizzaItem(
        onEditClick = { },
        onDeleteClick = { }
    )
}

@Composable
fun BebidaItem(
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
    ){
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {

            Column(
                Modifier.padding(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
//                        .padding(2.dp)
                ) {
                    Text(
                        text = "1. Bebida: R$",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2D2A20)
                    )

                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Remover",
                            tint = Color(0xFF2D2A20)
                        )
                    }
                }
            }
        }
    }
}