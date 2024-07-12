package com.healthcare.disease.data.diseasedetails.repository

import com.healthcare.disease.data.diseasedetails.mapper.toResponseModel
import com.healthcare.disease.datasource.dao.DiseaseDao
import com.healthcare.disease.domain.dashboard.model.DiseaseModel
import com.healthcare.disease.domain.diseaseDetail.repository.DiseaseDetailsRepository
import javax.inject.Inject

class DiseaseDetailsRepositoryImpl @Inject constructor(
    private val diseaseDao: DiseaseDao
) : DiseaseDetailsRepository {
    override suspend fun getDiseaseDetail(medicationId: Int): DiseaseModel =
        diseaseDao.getDiseaseDetail(medicationId).toResponseModel()
}