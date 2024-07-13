package com.healthcare.disease.domain.dashboard.repository

interface UserRepository {
    suspend fun saveUsername(username: String)
    suspend fun getUsername(): String?
    suspend fun logoutUserSession()
}