package com.healthcare.disease.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screen<T>(
    private val path: String,
    private val parameters: List<NamedNavArgument> = emptyList(),
    private val argsNames: T
) {
    val referencePath: String get() = path + parameters.map { "/{${it.name}}" }

    protected fun buildNavigationPath(vararg args: String): String =
        if (parameters.isEmpty()) path else path + args.map { "/$it" }

    fun buildComposable(
        navGraphBuilder: NavGraphBuilder,
        content: @Composable AnimatedContentScope.(backStackEntry: NavBackStackEntry) -> Unit
    ) = navGraphBuilder.composable(referencePath, parameters, content = content)

    fun buildComposableWithArgs(
        navGraphBuilder: NavGraphBuilder,
        content: @Composable (backStackEntry: NavBackStackEntry, args: T) -> Unit
    ) = navGraphBuilder.composable(referencePath, parameters) { content(it, argsNames) }

    // All Screens
//    data object Splash : Screen<Unit>("splash", argsNames = Unit)
    data object LoginFlow : Screen<Unit>("login_flow", argsNames = Unit)
    data object Dashboard : Screen<ScreenArgs.Dashboard>(
        "dashboard",
        listOf(navArgument(ScreenArgs.Dashboard.userName) {}),
        ScreenArgs.Dashboard
    ) {
        fun navigationPath(userName: String): String = buildNavigationPath(userName)
    }

    data object DiseaseDetail : Screen<ScreenArgs.DiseaseDetail>(
        "disease_detail",
        listOf(navArgument(ScreenArgs.DiseaseDetail.medicationId) {}),
        ScreenArgs.DiseaseDetail
    ) {
        fun navigationPath(medicationId: String): String = buildNavigationPath(medicationId)
    }
}

/**
 * Object to hold screen specific args names
 */
object ScreenArgs {
    object Dashboard {
        const val userName = "username"
    }

    object DiseaseDetail {
        const val medicationId = "medicationId"
    }
}
