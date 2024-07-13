package com.healthcare.disease.domain.usecase

import com.healthcare.disease.domain.dashboard.repository.UserRepository
import com.healthcare.disease.domain.dashboard.usecase.SaveUserdataUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Test
import kotlin.test.assertEquals

class SaveUserdataUseCaseTest {
    private val fakeRepo = object : UserRepository {
        private var username: String? = null

        override suspend fun saveUsername(username: String) {
            this.username = username
        }

        override suspend fun getUsername(): String? = username

        override suspend fun logoutUserSession() {
            this.username = null
        }
    }

    @Test
    fun `save the given username to preference`() {
        val username = "Guest"
        val saveUserdataUseCase = SaveUserdataUseCase(fakeRepo)
        CoroutineScope(Dispatchers.IO).launch {
            saveUserdataUseCase.saveUsername(username)
            assertEquals(expected = username, saveUserdataUseCase.getUsername())
        }
    }

    @Test
    fun `logout current user session`(){
        val saveUserdataUseCase = SaveUserdataUseCase(fakeRepo)
        CoroutineScope(Dispatchers.IO).launch {
            saveUserdataUseCase.logoutUserSession()
            assertEquals(expected = null, saveUserdataUseCase.getUsername())
        }
    }
}