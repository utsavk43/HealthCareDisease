package com.healthcare.disease.domain.common.state

fun <T, R> UiState<T>.whenSuccess(onSuccess: T.() -> R?): R? {
    if (this is UiState.Success) {
        return onSuccess(this.viewState)
    }

    return null
}

fun <T, R> UiState<T>.requireSuccess(onSuccess: T.() -> R): R =
    onSuccess((this as UiState.Success).viewState)
