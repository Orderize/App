package com.orderize.orderize.ui.forgotpassword

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForgotPasswordViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<ForgotPasswordUiState> = MutableStateFlow(ForgotPasswordUiState())
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
                onResetPasswordButtonClicked = { email ->
                    resetPassword(email)
                }
            )
        }
    }

    private fun resetPassword(email: String) {
        if (!_uiState.value.isEmailValid()) {
            _uiState.update { currentState ->
                currentState.copy(
                    alertPhrase = "Por favor, insira um email válido!"
                )
            }
            return
        }

        // Simula o processo de reset de senha. Aqui, você pode adicionar a lógica real.
        if (email.equals("zezinho@gmail.com")) {
            _uiState.update { currentState ->
                currentState.copy(
                    alertPhrase = "Instruções para resetar sua senha foram enviadas para $email"
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    alertPhrase = "Email não encontrado. Tente novamente!"
                )
            }
        }
    }
}
