package com.orderize.orderize.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orderize.orderize.R
import com.orderize.orderize.model.MockOrder
import com.orderize.orderize.ui.theme.orderizeGray
import com.orderize.orderize.ui.theme.strokeGray
import java.time.LocalTime

// TODO: CARD INCOMPLETO
@Composable
fun OrderCard(
    item: MockOrder,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(1.dp, strokeGray, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

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
                        contentDescription = "Clock"
                    )

                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .offset(
                                x = -22.dp,
                                y = 2.dp
                            ),
                        text = "${item.createdTime}",
                        color = strokeGray,
                        fontSize = 24.sp,
                    )

                }
            }

            Column {  }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderCardPreview(modifier: Modifier = Modifier) {
    val item = MockOrder(
        12,
        "Salão",
        "Pendente",
        LocalTime.of(21, 12)
    )
    OrderCard(item)
}