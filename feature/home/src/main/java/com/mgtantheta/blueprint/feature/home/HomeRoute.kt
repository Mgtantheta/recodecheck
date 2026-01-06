package com.mgtantheta.blueprint.feature.home

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute : NavKey

fun EntryProviderScope<NavKey>.homeScreen() =
    entry<HomeRoute> {
        HomeScreen()
    }
