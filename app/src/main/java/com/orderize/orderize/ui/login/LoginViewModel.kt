package com.orderize.orderize.ui.login

import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<LoginScreenUiState> = MutableStateFlow(LoginScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onEmailChange = {
                    _uiState.update { state ->
                        state.copy(
                            email = it
                        )
                    }
                },
                onPasswordChange = {
                    _uiState.update { state ->
                        state.copy(
                            password = it
                        )
                    }
                },
                onLoginButtonClicked = { email, password ->
                    login(email, password)
                }
            )
        }
    }

    private fun login(email: String, password: String) {
        if (_uiState.value.isAlertPhraseNotBlank()) {
            _uiState.update { currentState ->
                currentState.copy(
                    alertPhrase = ""
                )
            }
        }

        if (!_uiState.value.isEmailAndPasswordNotBlank()) {
            _uiState.update { currentState ->
                currentState.copy(
                    alertPhrase = "Os campos email e senha devem estar preenchidos!"
                )
            }
            return
        }

        if (email.equals("zezinho@gmail.com") && password.equals("123")) {
            _uiState.update { currentState ->
                currentState.copy(
                    userLogged = true,
                    userType = 1
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    alertPhrase = "Login inváido, email ou senha incorretos!"
                )
            }
        }
    }

}