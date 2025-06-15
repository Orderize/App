package com.orderize.orderize.ui.writeOrder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.orderize.orderize.repository.GeminiRepository
import com.orderize.orderize.ui.common.component.TopBar
import com.orderize.orderize.ui.gemini.GeminiViewModel
import com.orderize.orderize.ui.theme.backgroundGreen
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter


@Composable
fun WriteOrderScreen(
    geminiViewModel: GeminiViewModel,
    viewModel: WriteOrderViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val textUiState = viewModel.uiState
    val geminiState by geminiViewModel.uiState.collectAsState()

    LaunchedEffect(textUiState.text) {
        snapshotFlow { textUiState.text }
            .debounce(3000)
            .filter { it.isNotBlank() }
            .collect { prompt ->
                geminiViewModel.transformText(prompt)
            }
    }

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
                            .padding(top = 40.dp)
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
                        value = textUiState.text,
                        onValueChange = {
                            viewModel.onTextChange(it)
                        },
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black),

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
            
            item{
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

                    Text(
                        text = "Pedido formatado:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .offset(y = 70.dp)
                            .padding(start = 85.dp)
                    )

                    if (geminiState.loading) CircularProgressIndicator(
                        color = Color.Black,
                        modifier = Modifier
                            .offset(y = 150.dp)
                            .padding(start = 150.dp)
                    )

                    when {
                        geminiState.response.isNotBlank() -> {
                            Box(
                                modifier = Modifier
                                    .offset(y = 100.dp)
                                    .padding(start = 85.dp, end = 16.dp)
                                    .height(170.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Text(
                                    text = geminiState.response,
                                    color = Color(0xFFFFFFFF),
                                )
                            }
                        }

                        geminiState.error.isNotBlank() -> {
                            Text(
                                text = "Sem formatação: ${textUiState.text}",
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .offset(y = 80.dp)
                                    .padding(start = 85.dp)
                            )
                        }
                    }

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
                        Text("Próximo >>")
                    }
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
        geminiViewModel = GeminiViewModel(GeminiRepository()),
        viewModel = WriteOrderViewModel(),
        navController = navController
    )
}
