package com.healthcare.disease.domain.diseaseDetail.usecase

import com.healthcare.disease.domain.diseaseDetail.repository.DiseaseDetailsRepository
import javax.inject.Inject

class DiseaseDetailsUseCase @Inject constructor(
    private val diseaseDetailsRepository: DiseaseDetailsRepository
) {

    suspend fun getDiseaseDetail(medicationId: Int) =
        diseaseDetailsRepository.getDiseaseDetail(medicationId)
}