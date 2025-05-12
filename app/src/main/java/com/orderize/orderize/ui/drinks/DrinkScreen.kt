package com.orderize.orderize.ui.drinks

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.R
import com.orderize.orderize.repository.AppDataStore
import com.orderize.orderize.repository.drink.DrinkRepository
import com.orderize.orderize.repository.drink.network.DrinkService
import com.orderize.orderize.repository.drink.network.IDrinkService
import com.orderize.orderize.ui.theme.backgroundGreen

@Composable
fun DrinkScreen(
    navController: NavController,
    viewModel: DrinkViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val filteredItems = state.items.filter { drink ->
        drink.first.contains(state.searchQuery, ignoreCase = true)
    }

    val selectedDrinks = state.selectedDrinks

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundGreen)
    ) {

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

                        Column(
                            modifier = Modifier
                                .offset(y = 25.dp)
                                .padding(end = 70.dp)
                                .heightIn(max = 150.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            if (selectedDrinks.isEmpty()) {
                                Text(
                                    text = "Selecione uma bebida!",
                                    color = Color(0xFF3A3C16),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            } else {
                                selectedDrinks.forEach { drinkName ->
                                    Row(
                                        modifier = Modifier
                                            .padding(vertical = 4.dp)
                                            .border(
                                                1.dp,
                                                Color(0xFF3A3C16),
                                                RoundedCornerShape(8.dp)
                                            )
                                            .padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = drinkName,
                                            color = Color(0xFF3A3C16),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Remover bebida",
                                            modifier = Modifier
                                                .size(18.dp)
                                                .clickable {
                                                    viewModel.toggleDrinkSelection(drinkName)

                                                },
                                            tint = Color.Red
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.baloon_03),
                        contentDescription = "Balão 02",
                        modifier = Modifier
                            .size(400.dp)
                            .padding(top = 5.dp, start = 60.dp)
                            .align(Alignment.TopEnd)
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                            .width(290.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SearchBar(
                            value = state.searchQuery,
                            onValueChange = viewModel::updateSearchQuery,
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(55.dp)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .heightIn(max = 280.dp)
                                .padding(end = 4.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp))
                        {

                            items(filteredItems.size) {
                                index ->
                                val drink = filteredItems[index]
                                val isSelected = selectedDrinks.contains(drink.first)
                                DrinkCard(
                                    name = drink.first,
                                    price = drink.second,
                                    isSelected = isSelected,
                                    onClick = {
                                        viewModel.toggleDrinkSelection(drink.first)

                                    }
                                )
                            }
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
                            .padding(top = 10.dp)
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
fun DrinkCard(name: String, price: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color(0xFFFFFFFF) else Color(0xFF999999)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .clickable { onClick() }
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

//@Preview(showBackground = true)
//@Composable
//private fun PreviewDrinkScreen() {
//    val navController = rememberNavController()
//    DrinkScreen(
//        viewModel = DrinkViewModel(),
//        navController = navController
//    )
//}

