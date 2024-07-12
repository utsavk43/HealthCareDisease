package com.healthcare.disease.datasource.module

import android.content.Context
import com.healthcare.disease.datasource.AppDatabase
import com.healthcare.disease.datasource.dao.DiseaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideDiseaseDao(appDatabase: AppDatabase): DiseaseDao {
        return appDatabase.getDiseaseDao()
    }
}