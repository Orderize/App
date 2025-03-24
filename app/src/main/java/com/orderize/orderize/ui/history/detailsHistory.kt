package com.orderize.orderize.ui.history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orderize.orderize.R
import com.orderize.orderize.ui.theme.OrderizeTheme

class detailsHistory : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrderizeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        DetalhesHist(
                            modifier = Modifier.weight(1f),
                            pedido = Pedido("061", "20:30", "16/02")
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetalhesHistPrev(){
    DetalhesHist(
        pedido = Pedido("061", "20:30", "16/02")
    )
}


@Composable
fun DetalhesHist(modifier: Modifier = Modifier, pedido: Pedido) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF7B806A))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(9.dp))
        Row {
            Image(
                painter = painterResource(id = R.drawable.seta),
                contentDescription = "Ícone de calendário",
                Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Detalhes",
                fontSize = 24.sp,
                color = Color.White
            )
        }
        CardPedidos(pedido = pedido)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
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
                Text(
                    text = "Itens do pedido",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp,
                    color = Color(0xFFB2B3A4)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    Text(
                        text = "Qtd",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        color = Color(0xFF3A3C16)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Itens",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        color = Color(0xFF3A3C16)
                    )
                }

                Row (modifier = Modifier.fillMaxWidth().padding(10.dp)){
                    Text(
                        text = "2",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A3C16)
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "Calabresa",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A3C16)
                    )
                    Spacer(modifier = Modifier.width(70.dp))
                    Text(
                        text = "R$80,00",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A3C16)
                    )

                }
                Spacer(modifier = Modifier.height(3.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(4.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.obs),
                        contentDescription = "Ícone de observação",
                        Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(37.dp))
                    Text(
                        text = "Sem cebola",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 15.sp,
                        color = Color(0xFFB2B3A4)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row (modifier = Modifier.fillMaxWidth().padding(10.dp)){
                    Text(
                        text = "1",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A3C16)
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "Marguerita",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A3C16)
                    )
                    Spacer(modifier = Modifier.width(70.dp))
                    Text(
                        text = "R$40,00",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A3C16)
                    )

                }
                Spacer(modifier = Modifier.height(5.dp))
                Row (modifier = Modifier.fillMaxWidth().padding(10.dp)){
                    Text(
                        text = "1",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A3C16)
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "Coca-Cola",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A3C16)
                    )
                    Spacer(modifier = Modifier.width(75.dp))
                    Text(
                        text = "R$15,00",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A3C16)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Row (modifier = Modifier.fillMaxWidth().padding(10.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.money),
                        contentDescription = "Ícone de dinheiro",
                        Modifier.size(30.dp),
                        alignment = Alignment.Center
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "R$135,00",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A3C16),
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }
}