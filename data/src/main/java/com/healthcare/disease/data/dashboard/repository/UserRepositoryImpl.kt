package com.healthcare.disease.data.dashboard.repository

import com.healthcare.disease.datasource.AppDataStorage
import com.healthcare.disease.domain.dashboard.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val preference: AppDataStorage
):UserRepository {
    private companion object {
        private const val PREF_USERNAME = "username"
    }

    override suspend fun saveUsername(username: String) {
        preference.putPreference(PREF_USERNAME, username)
    }

    override suspend fun getUsername(): String? =
        preference.getPreference(PREF_USERNAME, "")

    override suspend fun logoutUserSession() {
        preference.clearAllPreference()
    }

}