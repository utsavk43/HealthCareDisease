package com.healthcare.disease.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.healthcare.disease.dashboard.DashboardScreen
import com.healthcare.disease.diseaseDetail.DiseaseDetailScreen
import com.healthcare.disease.login.LoginScreen

@Composable
fun NavGraph(navigationController: NavHostController) {

    NavHost(
        navController = navigationController,
        startDestination = Screen.LoginFlow.referencePath
    ) {
        composableScreen(screen = Screen.LoginFlow) {
            LoginScreen(navController = navigationController)
        }

        composableScreenWithArgs(screen = Screen.Dashboard) { navBackStackEntry, args ->
            navBackStackEntry.arguments?.run {
                DashboardScreen(
                    userName = getString(args.userName).toString(),
                    navController = navigationController
                )
            }
        }
        composableScreenWithArgs(screen = Screen.DiseaseDetail) { navBackStackEntry, args ->
            navBackStackEntry.arguments?.run {
                DiseaseDetailScreen(
                    medicationId = getString(args.medicationId).toString(),
                    navController = navigationController
                )
            }
        }
    }
}
