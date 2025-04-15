package com.orderize.orderize.ui.drinks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.R
import com.orderize.orderize.ui.common.component.TopBar
import com.orderize.orderize.ui.theme.backgroundGreen



@Composable
fun DrinkScreen(
    viewModel: DrinkViewModel = DrinkViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundGreen)
    ) {
        TopBar(navController = navController)

        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {

                    Image(
                        painter = painterResource(R.drawable.baloon_01),
                        contentDescription = "Balão 01",
                        modifier = Modifier
                            .size(350.dp)
                            .padding(top = 40.dp)
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = 50.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.drink),
                            contentDescription = "refri",
                            modifier = Modifier
                                .size(200.dp)
                                .padding(top = 60.dp)
                        )

                        Text(
                            text = "Selecione uma bebida!",
                            color = Color(0xFF3A3C16),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .offset(y = 25.dp)
                                .padding(end = 70.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Image(
                        painter = painterResource(R.drawable.baloon_02),
                        contentDescription = "Balão 01",
                        modifier = Modifier
                            .size(350.dp)
                            .padding(top = 30.dp)
                            .align(Alignment.TopEnd)

                    )
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .height(60.dp)
                            .width(250.dp),
                        shape = RoundedCornerShape(30),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEAE5DE),
                            contentColor = Color.Black
                        ),
                        elevation = ButtonDefaults.buttonElevation(4.dp)
                    ) {
                        Text("Finalizar >>")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDrinkScreen() {
    val navController = rememberNavController()
    DrinkScreen(
        viewModel = DrinkViewModel(),
        navController = navController
    )
}