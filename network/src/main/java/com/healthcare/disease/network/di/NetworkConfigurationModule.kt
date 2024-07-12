package com.healthcare.disease.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface NetworkConfigurationModule {

    @Singleton
    @Binds
    fun providesNetworkConfiguration(configurationImpl: NetworkConfigurationImpl): NetworkConfiguration
}
