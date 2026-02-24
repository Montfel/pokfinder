package com.montfel.pokfinder.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.montfel.pokfinder.feature.home.ui.HomeRoute
import com.montfel.pokfinder.feature.profile.ui.ProfileRoute

@Composable
fun NavigationComponent(
    deepLink: String?,
) {
    val backStack = rememberNavBackStack(Screen.Home)
    val onNavigateToProfile: (Int) -> Unit = { pokemonId ->
        backStack.add(Screen.Profile(pokemonId))
    }

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = dropUnlessResumed(block = backStack::removeLastOrNull),
        entryProvider = entryProvider {
            entry<Screen.Home> {
                HomeRoute(
                    deepLink = deepLink,
                    onNavigateToProfile = onNavigateToProfile,
                    onNavigateToDeepLink = { deepLink ->
                        runCatching {
//                                navController.navigate(deepLink)
                        }
                    }
                )
            }
            entry<Screen.Profile> { key ->
                ProfileRoute(
                    id = key.id,
                    onNavigateToProfile = onNavigateToProfile,
                    onNavigateBack = dropUnlessResumed(block = backStack::removeLastOrNull)
                )
            }
        },
    )
}
