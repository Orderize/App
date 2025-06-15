package com.orderize.orderize.ui.login

import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.R
import com.orderize.orderize.ui.navigation.ForgetPasswordRoute
import com.orderize.orderize.ui.navigation.HomePizzaioloRoute
import com.orderize.orderize.ui.navigation.LoginRoute
import com.orderize.orderize.ui.navigation.OrderCreateRoute
import com.orderize.orderize.ui.theme.mossGreen

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    LoginScreen(
        state,
        navController,
        modifier
    )
}

@Composable
fun LoginScreen(
    state: LoginScreenUiState = LoginScreenUiState(),
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.img_login_background),
                contentScale = ContentScale.FillHeight
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusRequesterPassword = remember { FocusRequester() }
        val email = state.email
        val password = state.password
        val alertPhrase = state.alertPhrase

        Image(
            painter = painterResource(R.drawable.img_title_orderize),
            contentDescription = "Title Orderize",
            modifier = Modifier
                .size(
                    size = 256.dp
                )
                .padding(top = 164.dp)
        )

        Spacer(Modifier.size(32.dp))

        Text(
            text = "Login",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier,
            color = Color.Black
        )

        Spacer(Modifier.size(22.dp))

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
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequesterPassword.requestFocus() }
            )
        )

        Spacer(modifier = Modifier.size(24.dp))

        TextField(
            value = password,
            onValueChange = state.onPasswordChange,
            label = { Text("Senha", color = Color.Black) },
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
                .padding(horizontal = 32.dp)
                .fillMaxWidth()
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(26.dp))
                .focusRequester(focusRequesterPassword),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { state.onLoginButtonClicked(email, password) }
            )
        )

        if (state.isAlertPhraseNotBlank()) {
            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = alertPhrase,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.size(24.dp))

        TextButton(
            onClick = {
                navController.navigate(ForgetPasswordRoute) {
                    popUpTo<LoginRoute> { inclusive = true}
                }
            }
        ) {
            Text(
                text = "Esqueci a senha",
                textDecoration = TextDecoration.Underline,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
                )
        }

        Spacer(modifier = Modifier.size(24.dp))

        if (state.loading) {
            CircularProgressIndicator(
                color = Color.Black,
                modifier = Modifier
                    .size(32.dp)
            )
        } else {
            Button(
                onClick = { state.onLoginButtonClicked(email, password) },
                modifier = Modifier
                    .widthIn(min = 200.dp)
                    .heightIn(min = 56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = mossGreen
                )
            ) {
                Text(
                    text = "Entrar",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }

        if (state.userLogged) {
            if (state.userType == 1) {
                navController.navigate(HomePizzaioloRoute) {
                    popUpTo<LoginRoute> { inclusive = true }
                    launchSingleTop = true
                }
            } else {
                navController.navigate(OrderCreateRoute) {
                    popUpTo<LoginRoute> { inclusive = true }
                    launchSingleTop = true
                }
            }
        }

    }
    
}

@Preview(showSystemUi = true)
@Composable
private fun LoginScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    LoginScreen(modifier = modifier, navController = navController)
}