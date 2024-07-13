package com.healthcare.disease.domain.dashboard.usecase

import com.healthcare.disease.domain.dashboard.repository.UserRepository
import javax.inject.Inject

class SaveUserdataUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun saveUsername(username: String) = userRepository.saveUsername(username)

    suspend fun getUsername() = userRepository.getUsername()

    suspend fun isSessionActive() = userRepository.getUsername()?.isNotBlank() ?: false

    suspend fun logoutUserSession() = userRepository.logoutUserSession()
}
