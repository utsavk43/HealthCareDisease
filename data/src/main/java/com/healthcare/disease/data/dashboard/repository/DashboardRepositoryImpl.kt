package com.healthcare.disease.data.dashboard.repository

import com.healthcare.disease.data.DashboardApiClient
import com.healthcare.disease.data.dashboard.mapper.toDiseaseEntity
import com.healthcare.disease.data.dashboard.mapper.toMedicationsClassesEntity
import com.healthcare.disease.data.dashboard.mapper.toResponseModel
import com.healthcare.disease.datasource.dao.DiseaseDao
import com.healthcare.disease.domain.dashboard.model.DiseaseModel
import com.healthcare.disease.domain.dashboard.repository.DashboardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val apiClient: DashboardApiClient,
    private val diseaseDao: DiseaseDao
) : DashboardRepository {

    override suspend fun diseaseProblems() {
        val diseaseResponse = apiClient.getDiseaseProblems()
        diseaseDao.insertDiseaseList(diseaseResponse.toDiseaseEntity())
        diseaseDao.insertMedicationsClasses(diseaseResponse.toMedicationsClassesEntity())
    }

    override fun getDiseaseProblems(): Flow<Map<String, List<DiseaseModel>>> {
        return diseaseDao.getDiseaseWithMedications().toResponseModel()
    }
}
