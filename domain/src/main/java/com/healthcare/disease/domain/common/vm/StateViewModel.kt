package com.healthcare.disease.domain.common.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.healthcare.disease.domain.common.state.UiState

abstract class StateViewModel<S> : ViewModel() {

    var uiState by mutableStateOf<UiState<S>>(UiState.Loading)
        protected set
}
