package com.orderize.orderize.ui.gemini

data class GeminiUiState(
    val response: String = "",
    val error: String = "",
    val loading: Boolean = false
)