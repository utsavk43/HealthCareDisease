package com.healthcare.disease.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.healthcare.disease.R
import com.healthcare.disease.diseaseDetail.DiseaseDetailScreen
import com.healthcare.disease.domain.common.state.requireSuccess
import com.healthcare.disease.domain.dashboard.model.DiseaseModel
import com.healthcare.disease.domain.dashboard.vm.DashboardViewModel
import com.healthcare.disease.ui.commonComposableView.BodyLargeText
import com.healthcare.disease.ui.commonComposableView.BodyMediumText
import com.healthcare.disease.ui.commonComposableView.BoxViewNoData
import com.healthcare.disease.ui.commonComposableView.DateHeader
import com.healthcare.disease.ui.commonComposableView.MediumTitleText
import com.healthcare.disease.ui.commonComposableView.TitleText
import com.healthcare.disease.ui.navigation.Screen
import com.healthcare.disease.ui.theme.AppTheme

@Composable
fun DashboardScreen(
    userName: String,
    navController: NavController,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
) {
    val uiState = dashboardViewModel.uiState

    Column {
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
                MediumTitleText(
                    modifier = Modifier
                        .padding(top = AppTheme.dimens.paddingLarge)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.lbl_hi, userName),
                    textAlign = TextAlign.Center
                )

                TitleText(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = when (dashboardViewModel.getGreetings()) {
                        1 -> stringResource(id = R.string.lbl_good_morning)
                        2 -> stringResource(id = R.string.lbl_good_afternoon)
                        else -> stringResource(id = R.string.lbl_good_evening)
                    },
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(AppTheme.dimens.paddingNormal))

        DashboardListView(
            data = uiState.requireSuccess { diseaseWithMedicationsList }
        ) { medicationId ->
            navController.navigate(
                Screen.DiseaseDetail.navigationPath(medicationId.toString())
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardListView(
    data: Map<String, List<DiseaseModel>>,
    onItemClick: (Int) -> Unit
) {
    BoxViewNoData(isEmpty = data.isEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 60.dp)
        ) {

            data.forEach {
                stickyHeader {
                    DateHeader(it.key)
                }
                items(it.value) { item ->
                    DashboardListItemCard(item, onItemClick)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun DashboardListItemCard(row: DiseaseModel, onItemClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(row.medicationId)
            }
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = R.drawable.jetpack_compose_logo)
                    .crossfade(enable = true)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = stringResource(id = R.string.lbl_drug_description)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .background(MaterialTheme.colorScheme.surfaceContainerLowest),
            verticalArrangement = Arrangement.Center
        ) {
            BodyLargeText(
                text = row.name.toString(), modifier = Modifier,
            )

            Row {
                BodyMediumText(
                    text = stringResource(id = R.string.lbl_dose), modifier = Modifier,
                )
                BodyMediumText(
                    text = row.dose.toString(), modifier = Modifier,
                )
            }

            Row {
                BodyMediumText(
                    text = stringResource(id = R.string.lbl_strength), modifier = Modifier,
                )
                BodyMediumText(
                    text = row.strength.toString(), modifier = Modifier,
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
            contentDescription = "",
            modifier = Modifier.padding(AppTheme.dimens.paddingSmall)
        )
    }

}