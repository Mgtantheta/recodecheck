package com.mgtantheta.blueprint.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.mgtantheta.blueprint.feature.home.HomeRoute
import com.mgtantheta.blueprint.feature.home.homeScreen

@Composable
fun BlueprintNavigation(
    modifier: Modifier = Modifier,
) {
    val navigationState =
        rememberNavigationState(
            startRoute = HomeRoute,
            topLevelRoutes = setOf(HomeRoute),
        )
    val navigator = remember { Navigator(navigationState) }

    val provider: (NavKey) -> NavEntry<NavKey> =
        entryProvider {
            homeScreen()
        }

    CompositionLocalProvider(LocalNavigator provides navigator) {
        NavDisplay(
            entries = navigationState.toEntries(provider),
            onBack = navigator::goBack,
            modifier = modifier,
        )
    }
}
