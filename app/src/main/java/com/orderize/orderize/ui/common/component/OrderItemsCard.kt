package com.orderize.orderize.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orderize.orderize.R
import com.orderize.orderize.model.MockPizza
import com.orderize.orderize.ui.theme.strokeGray
import com.orderize.orderize.ui.theme.textGray

@Composable
fun OrderItemsCard(
    modifier: Modifier = Modifier,
    items: List<MockPizza> = listOf(MockPizza(), MockPizza()),
    showCheckBox: Boolean = false,
    onCheckBoxClicked: (MockPizza) -> Unit = {}
) {
    Card(
        modifier = modifier
            .border(1.dp, strokeGray, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Itens do pedido",
                color = textGray,
                fontSize = 22.sp
            )

            Row {
                Text(
                    text = "Qtd",
                    color = Color.Black,
                    fontSize = 22.sp
                )

                Spacer(Modifier.width(32.dp))

                Text(
                    text = "Itens",
                    color = Color.Black,
                    fontSize = 22.sp
                )
            }

            LazyColumn {
                items(items) { pizza ->
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = pizza.qtt.toString(),
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(
                                        horizontal = 8.dp
                                    ),
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(Modifier.width(36.dp))

                            Text(
                                text = pizza.name,
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            if (showCheckBox) {
                                Checkbox(
                                    checked = pizza.isChecked,
                                    onCheckedChange = { onCheckBoxClicked(pizza) }
                                )
                            }
                        }
                    }

                    if (pizza.observations.isNotBlank()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_observations),
                                contentDescription = "Ícone observação",
                                modifier = Modifier
                                    .padding(
                                        horizontal = 2.dp
                                    )
                            )

                            Spacer(Modifier.width(30.dp))

                            Text(
                                text = pizza.observations,
                                color = textGray,
                                fontSize = 14.sp
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun OrderItemCardPreview() {
    OrderItemsCard(
        modifier = Modifier
            .heightIn(min = 150.dp, max = 250.dp)
            .fillMaxWidth()
    )
}