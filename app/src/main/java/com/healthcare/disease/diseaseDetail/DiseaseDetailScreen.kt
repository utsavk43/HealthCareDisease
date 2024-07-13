package com.healthcare.disease.diseaseDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.healthcare.disease.R
import com.healthcare.disease.domain.common.state.requireSuccess
import com.healthcare.disease.domain.diseaseDetail.vm.DiseaseDetailsViewModel
import com.healthcare.disease.ui.commonComposableView.BodyLargeText
import com.healthcare.disease.ui.commonComposableView.BodyMediumText
import com.healthcare.disease.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiseaseDetailScreen(
    medicationId: String,
    navController: NavController,
    viewModel: DiseaseDetailsViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = Unit) {
        viewModel.getDiseaseDetails(medicationId.toInt())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.lbl_disease_details))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.lbl_back)
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.paddingLarge)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = AppTheme.dimens.paddingLarge)
                        .padding(bottom = AppTheme.dimens.paddingExtraLarge)
                ) {
                    BodyLargeText(
                        text = uiState.requireSuccess { diseaseDetails }?.name.toString(),
                        modifier = Modifier,
                    )

                    Row {
                        BodyMediumText(
                            text = stringResource(id = R.string.lbl_dose), modifier = Modifier,
                        )
                        BodyMediumText(
                            text = uiState.requireSuccess { diseaseDetails }?.dose.toString(),
                            modifier = Modifier,
                        )
                    }

                    Row {
                        BodyMediumText(
                            text = stringResource(id = R.string.lbl_strength), modifier = Modifier,
                        )
                        BodyMediumText(
                            text = uiState.requireSuccess { diseaseDetails }?.strength.toString(),
                            modifier = Modifier,
                        )
                    }

                }
            }
        }
    }
}
