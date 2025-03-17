package com.orderize.orderize.ui.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.R

@Preview(showBackground = true)
    @Composable
    private fun HistoricoPrev(){
        val navController = rememberNavController()
        History(navController)
    }

    @Composable
    fun History(
        navController: NavController,
        modifier: Modifier = Modifier
    ){
        val valorPesquisa = remember { mutableStateOf("") }
        Column (modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF7B806A))
        ){
            Spacer(Modifier.height(10.dp))
            TextField(
                value = valorPesquisa.value,
                label = {
                    Text(text = "Pesquise...")
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.iconlupa),
                        contentDescription = "Ícone de busca",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },

                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth().height(30.dp),
                onValueChange = {
                    valorPesquisa.value = it
                },
            )
            Spacer(Modifier.height(10.dp))
            ListaPedidos()
        }
    }

    data class Pedido(
        val numero: String,
        val horario: String,
        val data: String
    )

    @Composable
    fun CardPedidos(pedido: Pedido) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(9.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            elevation = CardDefaults.cardElevation(8.dp) // sombra
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row ( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(130.dp)){
                    Text(text = "Pedido nº${pedido.numero}", style = MaterialTheme.typography.titleMedium)
                    Image(
                        painter = painterResource(id = R.drawable.delivery),
                        contentDescription = "Ícone de moto",
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(18.dp) // Espaço entre relogio/hora e calendário/data
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.clock),
                            contentDescription = "Ícone de relógio",
                        )
                        Text(text = pedido.horario, style = MaterialTheme.typography.bodyMedium)
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = "Ícone de calendário",
                        )
                        Text(text = pedido.data, style = MaterialTheme.typography.bodyMedium)
                    }
                }

            }
        }
    }

    @Composable
    fun ListaPedidos() {
        val pedidos = listOf(
            Pedido("058", "21:30", "15/02"),
            Pedido("059", "22:10", "15/02"),
            Pedido("060", "19:45", "16/02"),
            Pedido("061", "20:30", "16/02")
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pedidos) { pedido ->
                CardPedidos(pedido = pedido)
            }
        }
    }