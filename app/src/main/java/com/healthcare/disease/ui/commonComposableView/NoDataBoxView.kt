package com.healthcare.disease.ui.commonComposableView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.healthcare.disease.R

@Composable
fun BoxViewNoData(
    isEmpty: Boolean,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        if (isEmpty) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BodyLargeText(text = stringResource(R.string.no_data_available))
                BodyMediumText(text = stringResource(R.string.msg_no_data_add_record))
            }
        } else {
            content.invoke(this)
        }
    }
}