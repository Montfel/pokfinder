package com.montfel.pokfinder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.montfel.pokfinder.feature.home.ui.HomeRoute
import com.montfel.pokfinder.feature.profile.ui.ProfileRoute
import com.montfel.pokfinder.navigation.Screen.Companion.ID

@Composable
fun NavigationComponent(
    deepLink: String?,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeRoute(
                deepLink = deepLink,
                onNavigateToProfile = { id ->
                    navController.navigate(Screen.Profile.createRoute(id))
                },
                onNavigateToDeepLink = { deepLink ->
                    runCatching {
                        navController.navigate(deepLink)
                    }
                }
            )
        }

        composable(
            route = Screen.Profile.route,
            arguments = listOf(
                navArgument(ID) {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt(ID) ?: 0

            ProfileRoute(
                id = id,
                onNavigateToProfile = { pokemonId ->
                    navController.navigate(Screen.Profile.createRoute(pokemonId))
                },
                onNavigateBack = navController::popBackStack
            )
        }
    }
}
