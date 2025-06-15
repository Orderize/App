package com.orderize.orderize.ui.pizza

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.R
import androidx.compose.ui.Modifier
import com.orderize.orderize.ui.theme.backgroundGray
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.orderize.orderize.ui.common.component.TopBar

@Composable
fun PizzaScreen(
    viewModel: PizzaViewModel = PizzaViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.value
    val descricaoSabores = viewModel.descricaoSabores.value
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundGray)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.ballon_pizza),
                        contentDescription = "Balão pizza",
                        modifier = Modifier
                            .size(750.dp)
                            .padding(top = 70.dp, end = 65.dp)
                            .align(Alignment.TopStart)
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 50.dp, end = 70.dp)
                            .width(280.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pizza),
                            contentDescription = "Pizza",
                            modifier = Modifier
                                .size(200.dp)
                        )

                        Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xFF3A3C16),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )

                            ) {
                                append("Tamanho:\n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontSize = 18.sp
                                )
                            ) {
                                append(descricaoSabores)
                            }
                        },
                        modifier = Modifier
                            .padding(top = 10.dp),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = "Massa da pizza:",
                            style = TextStyle(
                                color = Color(0xFF3A3C16),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp)
                            )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF3A3C16)
                                ),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text("Fina")
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF3A3C16)
                                ),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text("Grossa")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = "Borda da pizza:",
                            style = TextStyle(
                                color = Color(0xFF3A3C16),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp)
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF3A3C16)
                                ),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text("Catupiry")
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF3A3C16)
                                ),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text("Cheddar")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF3A3C16)
                                ),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text("Requeijão")
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF3A3C16)
                                ),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text("Sem borda")
                            }
                        }


                    }
                }

                Spacer(modifier = Modifier.weight(1f))


            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .height(60.dp)
                            .width(250.dp),
                        shape = RoundedCornerShape(30),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4D5338),
                            contentColor = Color.White
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
private fun PreviewPizzaScreen() {
    val navController = rememberNavController()
    PizzaScreen(
        viewModel = PizzaViewModel(),
        navController = navController
    )
}
