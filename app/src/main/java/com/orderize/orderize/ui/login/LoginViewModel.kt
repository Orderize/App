package com.orderize.orderize.ui.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orderize.orderize.di.module.repositoryModule
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.repository.login.LoginRepository
import com.orderize.orderize.repository.login.local.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginScreenUiState> =
        MutableStateFlow(LoginScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        verifyUserLogged()

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

        viewModelScope.launch {
            val loginResponse = loginRepository.login(email, password)
            if (loginResponse is NetResource.Success) {
                _uiState.update { currentState ->
                    currentState.copy(
                        userLogged = true,
                        userType = if (loginResponse.data.role == "PIZZA_MAKER") 1 else 2
                    )
                }
            } else {
                Log.i(this.toString(), (loginResponse as NetResource.Fail).toString())
                _uiState.update { currentState ->
                    currentState.copy(
                        alertPhrase = loginResponse.message
                    )
                }
            }
        }
    }

    private fun verifyUserLogged() {
        viewModelScope.launch {
            val token = loginRepository.getApiToken()
            if (token != null) {
                val savedUser = loginRepository.getSavedUser(token)
                if (savedUser != null) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            userLogged = true,
                            userType = if (savedUser.role == "PIZZA_MAKER") 1 else 2
                        )
                    }
                }
            }
        }
    }

}