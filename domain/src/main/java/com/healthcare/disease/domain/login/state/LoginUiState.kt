package com.healthcare.disease.domain.login.state

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isPasswordValid: Boolean = false,
    val formError: LoginError? = null
)

sealed class LoginError : IllegalStateException() {
    data object NotRegistered : LoginError()
    data object Unknown : LoginError()
}
