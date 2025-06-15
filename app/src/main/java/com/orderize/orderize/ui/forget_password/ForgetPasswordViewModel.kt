package com.orderize.orderize.ui.forget_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.repository.reset_password.ResetPasswordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForgetPasswordViewModel (
    private val resetPasswordRepository: ResetPasswordRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<ForgotPasswordUiState> = MutableStateFlow(ForgotPasswordUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onEmailChange = { newEmail ->
                    _uiState.update { state ->
                        state.copy(
                            email = newEmail,
                            alertPhrase = "",
                            isPasswordResetSucessfull = null
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
                    alertPhrase = "Por favor, insira um email válido!",
                    isPasswordResetSucessfull = false
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    alertPhrase = "",
                    isPasswordResetSucessfull = null
                )
            }

            val result = resetPasswordRepository.resetPassword(email)

            when (result) {
                is NetResource.Success -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            alertPhrase = "Instruções para resetar sua senha foram enviadas para $email",
                            isPasswordResetSucessfull = true
                        )
                    }
                }

                is NetResource.Fail -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            alertPhrase = result.message ?: "Ocorreu um erro desconhecido ao solicitar a redefinição de senha.",
                            isPasswordResetSucessfull = false
                        )
                    }
                }

                is NetResource.Processing -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = true,
                            alertPhrase = "",
                            isPasswordResetSucessfull = null
                        )
                    }
                }
            }
        }

//        // Simula o processo de reset de senha. Aqui, você pode adicionar a lógica real.
//        if (email.equals("zezinho@gmail.com")) {
//            _uiState.update { currentState ->
//                currentState.copy(
//                    alertPhrase = "Instruções para resetar sua senha foram enviadas para $email"
//                )
//            }
//        } else {
//            _uiState.update { currentState ->
//                currentState.copy(
//                    alertPhrase = "Email não encontrado. Tente novamente!"
//                )
//            }
//        }
    }
}
