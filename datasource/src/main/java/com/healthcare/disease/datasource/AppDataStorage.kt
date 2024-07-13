package com.healthcare.disease.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.healthcare.disease.datasource.extensions.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class AppDataStorage @Inject constructor(@ApplicationContext private val context: Context) {
    private val dataSource get() = context.dataStore

    suspend fun getPreference(key: String, value: String): String {
        return (dataSource.data.firstOrNull()?.get(stringPreferencesKey(key))) ?: value
    }

    suspend fun putPreference(key: String, value: String) {
        dataSource.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    suspend fun clearAllPreference() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
