package com.healthcare.disease.domain.dashboard.vm

import androidx.lifecycle.viewModelScope
import com.healthcare.disease.domain.common.state.UiState
import com.healthcare.disease.domain.common.state.requireSuccess
import com.healthcare.disease.domain.common.vm.StateViewModel
import com.healthcare.disease.domain.dashboard.model.DiseaseModel
import com.healthcare.disease.domain.dashboard.state.DashboardError
import com.healthcare.disease.domain.dashboard.state.DashboardUiState
import com.healthcare.disease.domain.dashboard.usecase.DashboardUseCase
import com.healthcare.disease.domain.dashboard.usecase.SaveUserdataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardUseCase: DashboardUseCase,
    private val saveUserdataUseCase: SaveUserdataUseCase,
) : StateViewModel<DashboardUiState>() {

    init {
        uiState = UiState.Success(DashboardUiState())
        callDiseaseProblems()
        getListOfDiseaseWithMedications()
    }

    private fun callDiseaseProblems() {
        val exception = CoroutineExceptionHandler { _, throwable ->
            if (throwable is IllegalStateException)
                updateUiState(updateFormError = DashboardError.NoInternetConnection)
            else
                updateUiState(updateFormError = DashboardError.Unknown)
        }
        viewModelScope.launch(Dispatchers.IO + exception) {
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

    fun setUsername(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserdataUseCase.saveUsername(username)
        }
        updateUiState(username)
    }

    fun getGreetings() = dashboardUseCase.getGreetings()

    fun logoutUserSession() {
        viewModelScope.launch {
            saveUserdataUseCase.logoutUserSession()
        }
    }

    private fun updateUiState(
        updateUsername: String = uiState.requireSuccess { username },
        updateDiseaseWithMedicationsList: Map<String, List<DiseaseModel>> =
            uiState.requireSuccess { diseaseWithMedicationsList },
        updateFormError: DashboardError? = uiState.requireSuccess { formError }
    ) {
        uiState =
            UiState.Success(
                DashboardUiState(
                    username = updateUsername,
                    diseaseWithMedicationsList = updateDiseaseWithMedicationsList,
                    formError = updateFormError
                )
            )
    }
}