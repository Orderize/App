package com.orderize.orderize.ui.drinks

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
    val state by viewModel.uiState.collectAsState()

    val filteredItems = state.items.filter { drink ->
        drink.first.contains(state.searchQuery, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundGreen)
    ) {
        TopBar(navController = navController)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.baloon_01),
                        contentDescription = "Balão 01",
                        modifier = Modifier
                            .size(350.dp)
                            .padding(top = 40.dp)
                    )

                    Row(
                        modifier = Modifier.padding(top = 50.dp),
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.baloon_02),
                        contentDescription = "Balão 02",
                        modifier = Modifier
                            .size(350.dp)
                            .padding(top = 30.dp)
                            .align(Alignment.TopEnd)
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 100.dp, end = 30.dp)
                            .width(300.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SearchBar(
                            value = state.searchQuery,
                            onValueChange = viewModel::updateSearchQuery,
                            modifier = Modifier.fillMaxWidth()
                        )

                        filteredItems.forEach { drink ->
                            DrinkCard(name = drink.first, price = drink.second)
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { },
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

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("Pesquise...", color = Color.White) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.iconlupa),
                contentDescription = "Ícone de busca",
                tint = Color.White
            )
        },
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .height(60.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(15.dp))
            .border(2.dp, Color.White, RoundedCornerShape(15.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true
    )
}

@Composable
fun DrinkCard(name: String, price: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp)
            .background(Color(0xFFEAE5DE), shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            color = Color(0xFF3A3C16),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = price,
            color = Color(0xFF3A3C16),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
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
