package com.orderize.orderize.ui.login

data class LoginScreenUiState(
    val email: String = "",
    val onEmailChange: (String) -> Unit = {},
    val password: String = "",
    val onPasswordChange: (String) -> Unit = {},
    val alertPhrase: String = "",
    val onLoginButtonClicked: (String, String) -> Unit = { s: String, s1: String -> },
    val loading: Boolean = false,
    val userLogged: Boolean = false,
    val userType: Int = 0
) {
    fun isEmailAndPasswordNotBlank() = email.isNotBlank() && password.isNotBlank()

    fun isAlertPhraseNotBlank() = alertPhrase.isNotBlank()
}
