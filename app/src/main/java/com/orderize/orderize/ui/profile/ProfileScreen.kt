package com.orderize.orderize.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.orderize.orderize.ui.theme.backgroundGreen

@Composable
fun ProfileScreen(
    state: ProfileScreenUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundGreen)
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
                    .weight(2f)
                    .height(46.dp)
            ) {
                Text(
                    text = "Qtd pizzas feitas no dia:",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_pizza),
                        contentDescription = "Ícone de pizza",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(Modifier.size(8.dp))

                    Text(
                        text = state.qttCookedPizzas.toString(),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        color = Color.Black
                    )
                }
            }

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
                    .weight(1f)
                    .height(46.dp)
            ) {
                Text(
                    text = "Tempo médio:",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_clock),
                        contentDescription = "Ícone de relófio",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(Modifier.size(8.dp))

                    Text(
                        text = "0 Min",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        color = Color.Black
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(8.dp)
                .height(46.dp)
        ) {

            Row {
                Column {
                    Text(
                        text = "Pizza mais produzida",
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                    )

                    Text(
                        text = if (state.qttCookedPizzas <= 0) "Você ainda não produziu pizzas"
                        else state.mostCookedPizza,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(
        state = ProfileScreenUiState()
    )
}