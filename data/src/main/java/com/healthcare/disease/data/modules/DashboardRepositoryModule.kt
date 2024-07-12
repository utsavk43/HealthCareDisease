package com.healthcare.disease.data.modules

import com.healthcare.disease.data.dashboard.repository.DashboardRepositoryImpl
import com.healthcare.disease.domain.dashboard.repository.DashboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DashboardRepositoryModule {

    @Singleton
    @Binds
    fun providesDashboardRepository(repositoryImpl: DashboardRepositoryImpl): DashboardRepository
}