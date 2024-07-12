package com.healthcare.disease

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HealthCareDiseaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}