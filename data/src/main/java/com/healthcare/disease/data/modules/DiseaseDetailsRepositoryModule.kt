package com.healthcare.disease.data.modules

import com.healthcare.disease.data.diseasedetails.repository.DiseaseDetailsRepositoryImpl
import com.healthcare.disease.domain.diseaseDetail.repository.DiseaseDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DiseaseDetailsRepositoryModule {

    @Singleton
    @Binds
    fun providesDiseaseDetailsRepository(repositoryImpl: DiseaseDetailsRepositoryImpl): DiseaseDetailsRepository
}