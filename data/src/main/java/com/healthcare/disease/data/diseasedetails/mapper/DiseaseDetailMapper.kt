package com.healthcare.disease.data.diseasedetails.mapper

import com.healthcare.disease.datasource.entity.MedicationsClassesEntity
import com.healthcare.disease.domain.dashboard.model.DiseaseModel

fun MedicationsClassesEntity.toResponseModel(): DiseaseModel =
    DiseaseModel(
        medicationId = id,
        name = name,
        dose = dose,
        strength = strength
    )