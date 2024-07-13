package com.healthcare.disease.data.modules

import com.healthcare.disease.data.dashboard.repository.DashboardRepositoryImpl
import com.healthcare.disease.data.dashboard.repository.UserRepositoryImpl
import com.healthcare.disease.domain.dashboard.repository.DashboardRepository
import com.healthcare.disease.domain.dashboard.repository.UserRepository
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

    @Singleton
    @Binds
    fun providesUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository
}