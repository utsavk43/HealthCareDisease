package com.healthcare.disease.domain.common.state

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()

    class Success<T>(val viewState: T) : UiState<T>()

    class Error(val error: Throwable) : UiState<Nothing>()
}
