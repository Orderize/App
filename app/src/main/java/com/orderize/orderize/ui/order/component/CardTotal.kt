package com.orderize.orderize.ui.order.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orderize.orderize.R
import com.orderize.orderize.ui.theme.verdeClaro

@Composable
fun CardTotal(modifier: Modifier = Modifier) {
    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Box(
                modifier = Modifier
                    .height(240.dp)
                    .width(360.dp)
                    .padding(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.balao_verde),
                    contentDescription = "Balão",
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(0.8f)
                        .padding(bottom = 24.dp)
                ) {
                    Text(
                        text = "TOTAL",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF2D2D2D)
                    )
                    Text(
                        text = "R$0,00",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Column {
                            Text(text = "Pizzas", color = verdeClaro, fontSize = 14.sp)
                            Text(text = "R$0,00", color = verdeClaro, fontSize = 14.sp)
                        }

                        Column {
                            Text(text = "Bebidas", color = verdeClaro, fontSize = 14.sp)
                            Text(text = "R$0,00", color = verdeClaro, fontSize = 14.sp)
                        }
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun CardTotalAberto() {
    CardTotal()
}