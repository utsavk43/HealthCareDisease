package com.healthcare.disease.network.modules

import com.healthcare.disease.data.DashboardApiClient
import com.healthcare.disease.network.di.NetworkConfiguration
import com.healthcare.disease.network.di.NetworkConfigurationModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkConfigurationModule::class])
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()


    @Singleton
    @Provides
    fun providesDashboardApiClient(
        httpClient: OkHttpClient,
        networkConfig: NetworkConfiguration
    ): DashboardApiClient = Retrofit.Builder()
        .baseUrl(networkConfig.apiBaseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DashboardApiClient::class.java)

}
