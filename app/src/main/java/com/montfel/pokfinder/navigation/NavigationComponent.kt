package com.montfel.pokfinder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.montfel.pokfinder.feature.home.ui.HomeRoute
import com.montfel.pokfinder.feature.profile.ui.ProfileRoute

@Composable
fun NavigationComponent(
    deepLink: String?,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeRoute(
                deepLink = deepLink,
                onNavigateToProfile = { id ->
                    navController.navigate(Screen.Profile(id))
                },
                onNavigateToDeepLink = { deepLink ->
                    runCatching {
                        navController.navigate(deepLink)
                    }
                }
            )
        }

        composable<Screen.Profile> {
            val (id) = it.toRoute<Screen.Profile>()

            ProfileRoute(
                id = id,
                onNavigateToProfile = { pokemonId ->
                    navController.navigate(Screen.Profile(pokemonId))
                },
                onNavigateBack = navController::popBackStack
            )
        }
    }
}
