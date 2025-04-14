package com.example.nodeschallenge.ui.state

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String? = null) : UiState<Nothing>()
}