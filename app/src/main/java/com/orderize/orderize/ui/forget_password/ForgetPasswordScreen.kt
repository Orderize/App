package com.orderize.orderize.ui.forget_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.R
import com.orderize.orderize.repository.reset_password.ResetPasswordRepository
import com.orderize.orderize.ui.navigation.ForgetPasswordRoute
import com.orderize.orderize.ui.navigation.LoginRoute
import com.orderize.orderize.ui.theme.mossGreen

@Composable
fun ForgetPasswordScreen(
    viewModel: ForgetPasswordViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()

    val alertTextColor = when (state.isPasswordResetSucessfull) {
        true -> Color.Green
        false ->  Color.Red
        null -> Color.Red
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.img_login_background),
                contentScale = ContentScale.FillHeight
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.img_title_orderize),
            contentDescription = "Title Orderize",
            modifier = Modifier
                .size(256.dp)
                .padding(top = 164.dp)
        )

        Spacer(Modifier.size(32.dp))

        Text(
            text = "Recuperar Senha",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier,
            color = Color.Black
        )

        Spacer(Modifier.size(22.dp))

        val focusRequesterEmail = remember { FocusRequester() }
        val email = state.email

        TextField(
            value = email,
            onValueChange = state.onEmailChange,
            label = { Text("Email", color = Color.Black) },
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
                .padding(horizontal = 32.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(26.dp)),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { state.onResetPasswordButtonClicked(email) }
            )
        )

        if (state.isAlertPhraseNotBlank()) {
            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = state.alertPhrase,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = alertTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.size(24.dp))

        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color.Black,
                modifier = Modifier
                    .size(32.dp)
            )
        } else {
            Button(
                enabled = !state.isLoading && state.isEmailValid(),
                onClick = { state.onResetPasswordButtonClicked(email) },
                modifier = Modifier
                    .widthIn(min = 200.dp)
                    .heightIn(min = 56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = mossGreen
                )
            ) {
                Text(
                    text = "Recuperar Senha",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.size(24.dp))

        TextButton(
            enabled = !state.isLoading,
            onClick = {
                navController.navigate(LoginRoute) {
                    popUpTo<ForgetPasswordRoute> { inclusive = true }
                }
            }
        ) {
            Text(
                text = "Voltar para Login",
                textDecoration = TextDecoration.Underline,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ForgotPasswordScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    //ForgotPasswordScreen(modifier = modifier, navController = navController, viewModel = ForgetPasswordViewModel())
}
