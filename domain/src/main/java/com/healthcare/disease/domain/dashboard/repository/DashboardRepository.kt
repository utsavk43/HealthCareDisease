package com.healthcare.disease.domain.dashboard.repository

import com.healthcare.disease.domain.dashboard.model.DiseaseModel
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    suspend fun diseaseProblems()
    fun getDiseaseProblems(): Flow<Map<String, List<DiseaseModel>>>
}