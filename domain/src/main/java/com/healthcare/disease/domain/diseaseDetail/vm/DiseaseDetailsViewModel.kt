package com.healthcare.disease.domain.diseaseDetail.vm

import androidx.lifecycle.viewModelScope
import com.healthcare.disease.domain.common.state.UiState
import com.healthcare.disease.domain.common.state.requireSuccess
import com.healthcare.disease.domain.common.vm.StateViewModel
import com.healthcare.disease.domain.dashboard.model.DiseaseModel
import com.healthcare.disease.domain.dashboard.state.DashboardUiState
import com.healthcare.disease.domain.diseaseDetail.state.DiseaseDetailsUiState
import com.healthcare.disease.domain.diseaseDetail.usecase.DiseaseDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiseaseDetailsViewModel @Inject constructor(
    private val diseaseDetailsUseCase: DiseaseDetailsUseCase
) : StateViewModel<DiseaseDetailsUiState>() {

    init {
        uiState = UiState.Success(DiseaseDetailsUiState())
    }

    fun getDiseaseDetails(medicationId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            updateUiState(diseaseDetailsUseCase.getDiseaseDetail(medicationId))
        }
    }

    private fun updateUiState(updateDiseaseDetails: DiseaseModel? = uiState.requireSuccess { diseaseDetails }) {
        uiState = UiState.Success(
            DiseaseDetailsUiState(diseaseDetails = updateDiseaseDetails)
        )
    }
}