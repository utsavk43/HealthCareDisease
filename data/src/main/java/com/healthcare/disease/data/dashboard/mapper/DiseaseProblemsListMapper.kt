package com.healthcare.disease.data.dashboard.mapper

import com.healthcare.disease.data.dashboard.dto.DiseaseProblemsListResponse
import com.healthcare.disease.data.dashboard.dto.Problem
import com.healthcare.disease.datasource.entity.DiseaseEntity
import com.healthcare.disease.datasource.entity.MedicationsClassesEntity
import com.healthcare.disease.domain.dashboard.model.DiseaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun DiseaseProblemsListResponse.toDiseaseEntity(): List<DiseaseEntity> {
    return problems?.flatMap { problem ->
        val asthmaEntities = problem?.asthma?.map { disease ->
            DiseaseEntity(id = disease?.id ?: 0, name = disease?.name ?: "")
        } ?: emptyList()

        val diabetesEntities = problem?.diabetes?.map { disease ->
            DiseaseEntity(id = disease?.id ?: 0, name = disease?.name ?: "")
        } ?: emptyList()

        asthmaEntities + diabetesEntities
    } ?: emptyList()
}

fun DiseaseProblemsListResponse.toMedicationsClassesEntity(): List<MedicationsClassesEntity> {
    return problems?.flatMap { problem ->
        val asthmaEntities = problem?.asthma?.flatMap { disease ->
            disease?.medications?.flatMap { medicationsClasses ->
                medicationsClasses?.medicationsClasses?.flatMap { className ->

                    val className1 = className?.className?.flatMap { associatedDrug ->
                        val associatedDrug1 = associatedDrug?.associatedDrug?.map { mBean ->
                            MedicationsClassesEntity(
                                id = mBean?.id ?: 0,
                                name = mBean?.name,
                                dose = mBean?.dose,
                                strength = mBean?.strength,
                                diseaseId = mBean?.diseaseId ?: 0
                            )
                        } ?: emptyList()
                        val associatedDrug2 = associatedDrug?.associatedDrug2?.map { mBean ->
                            MedicationsClassesEntity(
                                id = mBean?.id ?: 0,
                                name = mBean?.name,
                                dose = mBean?.dose,
                                strength = mBean?.strength,
                                diseaseId = mBean?.diseaseId ?: 0
                            )
                        } ?: emptyList()

                        associatedDrug1.plus(associatedDrug2)
                    } ?: emptyList()

                    val className2 = className?.className2?.flatMap { associatedDrug ->
                        val associatedDrug1 = associatedDrug?.associatedDrug?.map { mBean ->
                            MedicationsClassesEntity(
                                id = mBean?.id ?: 0,
                                name = mBean?.name,
                                dose = mBean?.dose,
                                strength = mBean?.strength,
                                diseaseId = mBean?.diseaseId ?: 0
                            )
                        } ?: emptyList()
                        val associatedDrug2 = associatedDrug?.associatedDrug2?.map { mBean ->
                            MedicationsClassesEntity(
                                id = mBean?.id ?: 0,
                                name = mBean?.name,
                                dose = mBean?.dose,
                                strength = mBean?.strength,
                                diseaseId = mBean?.diseaseId ?: 0
                            )
                        } ?: emptyList()

                        associatedDrug1.plus(associatedDrug2)
                    } ?: emptyList()

                    className1.plus(className2)
                } ?: emptyList()
            } ?: emptyList()
        } ?: emptyList()
        val diabetesEntities = problem?.diabetes?.flatMap { disease ->
            disease?.medications?.flatMap { medicationsClasses ->
                medicationsClasses?.medicationsClasses?.flatMap { className ->
                    val className1 = className?.className?.flatMap { associatedDrug ->
                        val associatedDrug1 = associatedDrug?.associatedDrug?.map { mBean ->
                            MedicationsClassesEntity(
                                id = mBean?.id ?: 0,
                                name = mBean?.name,
                                dose = mBean?.dose,
                                strength = mBean?.strength,
                                diseaseId = mBean?.diseaseId ?: 0
                            )
                        } ?: emptyList()
                        val associatedDrug2 = associatedDrug?.associatedDrug2?.map { mBean ->
                            MedicationsClassesEntity(
                                id = mBean?.id ?: 0,
                                name = mBean?.name,
                                dose = mBean?.dose,
                                strength = mBean?.strength,
                                diseaseId = mBean?.diseaseId ?: 0
                            )
                        } ?: emptyList()

                        associatedDrug1.plus(associatedDrug2)
                    } ?: emptyList()
                    val className2 = className?.className2?.flatMap { associatedDrug ->
                        val associatedDrug1 = associatedDrug?.associatedDrug?.map { mBean ->
                            MedicationsClassesEntity(
                                id = mBean?.id ?: 0,
                                name = mBean?.name,
                                dose = mBean?.dose,
                                strength = mBean?.strength,
                                diseaseId = mBean?.diseaseId ?: 0
                            )
                        } ?: emptyList()
                        val associatedDrug2 = associatedDrug?.associatedDrug2?.map { mBean ->
                            MedicationsClassesEntity(
                                id = mBean?.id ?: 0,
                                name = mBean?.name,
                                dose = mBean?.dose,
                                strength = mBean?.strength,
                                diseaseId = mBean?.diseaseId ?: 0
                            )
                        } ?: emptyList()

                        associatedDrug1.plus(associatedDrug2)
                    } ?: emptyList()
                    className1.plus(className2)
                } ?: emptyList()
            } ?: emptyList()
        } ?: emptyList()

        asthmaEntities.plus(diabetesEntities)
    } ?: emptyList()
}

fun Flow<Map<DiseaseEntity, List<MedicationsClassesEntity>>>.toResponseModel():
        Flow<Map<String, List<DiseaseModel>>> =
    this.map { originalMap ->
        originalMap.mapKeys { (diseaseEntity, _) ->
            diseaseEntity.name
        }.mapValues { (_, medicationsClassesEntities) ->
            medicationsClassesEntities.map { mBean ->
                DiseaseModel(
                    medicationId = mBean.id,
                    name = mBean.name,
                    dose = mBean.dose,
                    strength = mBean.strength
                )
            }
        }
    }