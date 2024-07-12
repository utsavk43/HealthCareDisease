package com.healthcare.disease.domain.diseaseDetail.repository

import com.healthcare.disease.domain.dashboard.model.DiseaseModel

interface DiseaseDetailsRepository {

    suspend fun getDiseaseDetail(medicationId: Int): DiseaseModel
}