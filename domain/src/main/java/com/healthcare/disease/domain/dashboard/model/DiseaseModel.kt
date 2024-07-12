package com.healthcare.disease.domain.dashboard.model

data class DiseaseModel(
    val medicationId: Int,
    val name: String?,
    val dose: String?,
    val strength: String?,
)
