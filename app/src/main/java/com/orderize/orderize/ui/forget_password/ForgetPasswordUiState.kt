package com.orderize.orderize.ui.forget_password

data class ForgotPasswordUiState(
    val email: String = "",
    val onEmailChange: (String) -> Unit = {},
    val alertPhrase: String = "",
    val onResetPasswordButtonClicked: (String) -> Unit = { email: String -> },
    val isLoading: Boolean = false,
    val isPasswordResetSucessfull: Boolean? = null

) {
    // Função para verificar se o email inserido é válido
    fun isEmailValid() = email.isNotBlank() && email.contains("@")

    // Função para verificar se a frase de alerta não está vazia
    fun isAlertPhraseNotBlank() = alertPhrase.isNotBlank()
}
