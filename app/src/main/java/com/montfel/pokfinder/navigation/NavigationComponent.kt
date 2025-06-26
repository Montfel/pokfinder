package com.montfel.pokfinder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.montfel.pokfinder.feature.home.ui.HomeRoute
import com.montfel.pokfinder.feature.profile.ui.ProfileRoute

@Composable
fun NavigationComponent(
    deepLink: String?,
) {
    val backStack = rememberNavBackStack(Screen.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Screen.Home> {
                HomeRoute(
                    deepLink = deepLink,
                    onNavigateToProfile = { id ->
                        backStack.add(Screen.Profile(id))
                    },
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
                    onNavigateToProfile = { pokemonId ->
                        backStack.add(Screen.Profile(pokemonId))
                    },
                    onNavigateBack = backStack::removeLastOrNull
                )
            }
        },
    )
}
