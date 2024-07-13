package com.healthcare.disease.domain.dashboard.state

import com.healthcare.disease.domain.dashboard.model.DiseaseModel

data class DashboardUiState(
    val username: String = "",
    val diseaseWithMedicationsList: Map<String, List<DiseaseModel>> = mapOf(),
    val formError: DashboardError? = null
)

sealed class DashboardError : IllegalStateException() {
    data object NoInternetConnection : DashboardError()
    data object Unknown : DashboardError()
}

