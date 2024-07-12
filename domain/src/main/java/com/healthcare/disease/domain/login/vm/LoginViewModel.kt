package com.healthcare.disease.domain.login.vm

import com.healthcare.disease.domain.common.state.UiState
import com.healthcare.disease.domain.common.state.requireSuccess
import com.healthcare.disease.domain.common.vm.StateViewModel
import com.healthcare.disease.domain.login.state.LoginError
import com.healthcare.disease.domain.login.state.LoginUiState
import com.healthcare.disease.domain.login.usecase.PasswordValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val passwordValidationUseCase: PasswordValidationUseCase
) : StateViewModel<LoginUiState>() {

    init {
        uiState = UiState.Success(LoginUiState())
    }

    fun setUserName(userName: String) = updateUiState(updatedUsername = userName)

    fun setPassword(updatedPassword: String) {
        updateUiState(
            updatedPassword = updatedPassword,
            updatedIsPasswordValid = passwordValidationUseCase.isPasswordValid(updatedPassword)
        )
    }

    private fun updateUiState(
        updatedUsername: String = uiState.requireSuccess { username },
        updatedPassword: String = uiState.requireSuccess { password },
        updatedIsPasswordValid: Boolean = uiState.requireSuccess { isPasswordValid },
        updatedFormError: LoginError? = uiState.requireSuccess { formError }
    ) {
        uiState = UiState.Success(
            LoginUiState(
                username = updatedUsername,
                password = updatedPassword,
                isPasswordValid = updatedIsPasswordValid,
                formError = updatedFormError
            )
        )
    }
}
