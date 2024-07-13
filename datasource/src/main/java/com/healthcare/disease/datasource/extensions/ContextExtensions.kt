package com.healthcare.disease.datasource.extensions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val APP_PREFERENCE_FILE_NAME = "healthcare_disease_preferences"

internal val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = APP_PREFERENCE_FILE_NAME)
