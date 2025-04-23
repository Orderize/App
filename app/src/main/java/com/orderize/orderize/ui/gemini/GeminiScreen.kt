package com.orderize.orderize.ui.gemini

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.ui.theme.mossGreen

@Composable
fun GeminiScreen(
    viewModel: GeminiViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    GeminiScreenContent(
        state = state,
        onTransformText = { viewModel.transformText(it) },
        modifier = modifier
    )
}

@Composable
fun GeminiScreenContent(
    state: GeminiUiState,
    onTransformText: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var texto by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Reescritor de Pedidos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Digite o pedido", color = Color.Black) },
            shape = RoundedCornerShape(26.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(26.dp)),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (state.loading) {
            CircularProgressIndicator(color = Color.Black)
        } else {
            Button(
                onClick = { onTransformText(texto) },
                modifier = Modifier
                    .widthIn(min = 200.dp)
                    .heightIn(min = 56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = mossGreen)
            ) {
                Text("Transformar", fontSize = 18.sp, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        when {
            state.response.isNotBlank() -> {
                Text(
                    text = "Pedido formatado:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = state.response, color = Color.DarkGray)
            }

            state.error.isNotBlank() -> {
                Text(
                    text = "Erro: ${state.error}",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewGeminiScreen() {
    val navController = rememberNavController()
    GeminiScreenContent(
        state = GeminiUiState(),
        onTransformText = {},
        modifier = Modifier
    )
}
