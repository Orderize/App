package com.orderize.orderize.model

sealed class NetResource<out T: Any> {
    data class Success<out T : Any>(val data: T) : NetResource<T>()
    data class Fail(val message: String) : NetResource<Nothing>()
    object Processing : NetResource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Successo -> $data"
            is Fail -> "Erro -> $message]"
            is Processing -> "Carregando"
        }
    }
}