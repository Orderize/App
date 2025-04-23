package com.orderize.orderize.ui.gemini

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orderize.orderize.repository.GeminiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GeminiViewModel(private val repository: GeminiRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<GeminiUiState> = MutableStateFlow(GeminiUiState())
    val uiState: StateFlow<GeminiUiState> = _uiState

    fun transformText(text: String) {
        _uiState.value = _uiState.value.copy(loading = true, response = "", error= "")

        viewModelScope.launch {
            val prompt = """
                Você é responsável por formatar pedidos de pizza. Sempre reescreva os pedidos mantendo a estrutura clara e padronizada, com as seguintes regras:

                1. Tamanhos disponíveis: grande, pequena, broto.
                2. A pizza ou esfirra pode ter ou não borda.
                3. Ingredientes podem ser removidos. Especifique quais foram retirados.
                4. Indique o sabor da pizza ou esfirra.
                5. Bebidas devem ser listadas separadamente.
                6. Não adicione observações.
                
                Formato padrão da resposta:
                
                [quantidade]x Pizza: [sabor], Tamanho: [tamanho] (se citado), Sem: [ingredientes removidos] (se houver), Borda: [sabor] (se houver).
                [quantidade]x Esfirra: [sabor], Tamanho: [tamanho] (se citado), Sem: [ingredientes removidos] (se houver), Borda: [sabor] (se houver).
                [quantidade]x Bebida: [nome], (se houver adicionais, listar com: Com: [adicional]).
                
                Agora, formate o seguinte pedido:
                
                "$text"
            """.trimIndent()

            val result = repository.rewriteText(prompt)
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    response = result.getOrNull() ?: ""
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = result.exceptionOrNull()?.message ?: "Erro desconhecido"
                )
            }
        }
    }
}