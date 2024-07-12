package com.healthcare.disease.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navArgument
import androidx.navigation.navigation

fun <T> NavGraphBuilder.composableScreenWithArgs(
    screen: Screen<T>,
    content: @Composable (backStackEntry: NavBackStackEntry, args: T) -> Unit
) = screen.buildComposableWithArgs(this, content)

fun <T> NavGraphBuilder.composableScreen(
    screen: Screen<T>,
    content: @Composable AnimatedContentScope.(backStackEntry: NavBackStackEntry) -> Unit
) = screen.buildComposable(this, content = content)
//endregion
