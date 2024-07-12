package com.healthcare.disease.domain.dashboard.vm

import androidx.lifecycle.viewModelScope
import com.healthcare.disease.domain.common.state.UiState
import com.healthcare.disease.domain.common.state.requireSuccess
import com.healthcare.disease.domain.common.vm.StateViewModel
import com.healthcare.disease.domain.dashboard.model.DiseaseModel
import com.healthcare.disease.domain.dashboard.state.DashboardUiState
import com.healthcare.disease.domain.dashboard.usecase.DashboardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardUseCase: DashboardUseCase,
) : StateViewModel<DashboardUiState>() {

    init {
        uiState = UiState.Success(DashboardUiState())
        callDiseaseProblems()
        getListOfDiseaseWithMedications()
    }

    private fun callDiseaseProblems() {
        viewModelScope.launch(Dispatchers.IO) {
            dashboardUseCase.diseaseProblems()
        }
    }

    private fun getListOfDiseaseWithMedications() {
        viewModelScope.launch {
            dashboardUseCase.getListOfDiseaseWithMedications().collectLatest {
                updateUiState(updateDiseaseWithMedicationsList = it)
            }
        }
    }

    fun setUsername(username: String) = updateUiState(username)

    fun getGreetings() = dashboardUseCase.getGreetings()

    private fun updateUiState(
        updateUsername: String = uiState.requireSuccess { username },
        updateDiseaseWithMedicationsList: Map<String, List<DiseaseModel>> =
            uiState.requireSuccess { diseaseWithMedicationsList },
    ) {
        uiState =
            UiState.Success(
                DashboardUiState(
                    username = updateUsername,
                    diseaseWithMedicationsList = updateDiseaseWithMedicationsList
                )
            )
    }
}