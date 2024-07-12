package com.healthcare.disease.container

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.healthcare.disease.ui.navigation.NavGraph

@Composable
fun AppContainer() {
    val appContainerState = rememberNavController()

    Box {
        NavGraph(appContainerState)
    }
}

@Preview
@Composable
private fun PreviewAppContainer() {
    AppContainer()
}
