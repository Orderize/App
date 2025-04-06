package com.orderize.orderize.ui.writeOrder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orderize.orderize.R
import com.orderize.orderize.ui.common.component.TopBar
import com.orderize.orderize.ui.theme.backgroundGreen


@Composable
fun WriteOrderScreen(
    viewModel: writeOrderViewModel = writeOrderViewModel(),
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
                        .fillMaxWidth()
                ) {

                    Image(
                        painter = painterResource(R.drawable.baloon_01),
                        contentDescription = "Balão 01",
                        modifier = Modifier
                            .size(350.dp)
                            .padding(top = 30.dp)
                    )

                    Text(
                        text = "Digite seu pedido:",
                        color = Color(0xFF3A3A2D),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .offset(y = 70.dp)
                            .padding(start = 32.dp)

                    )

                    Divider(
                        color = Color(0xFFFFFFFF),
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .offset(y = 100.dp)
                            .width(350.dp)
                    )

                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        textStyle = TextStyle(fontSize = 20.sp),
                        modifier = Modifier
                            .width(350.dp)
                            .height(150.dp)
                            .offset(y = 130.dp)
                            .fillMaxWidth(),
                        placeholder = { Text("...") },
                        maxLines = 5,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewWriteOrderScreen() {
    val navController = rememberNavController()
    WriteOrderScreen(
        viewModel = writeOrderViewModel(),
        navController = navController
    )
}
