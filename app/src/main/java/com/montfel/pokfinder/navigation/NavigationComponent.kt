package com.montfel.pokfinder.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.montfel.pokfinder.presentation.home.HomeScreen
import com.montfel.pokfinder.presentation.profile.ProfileScreen

@ExperimentalMaterialApi
@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToProfile = { id ->
                    navController.navigate(Screen.Profile.createRoute(id))
                }
            )
        }

        composable(
            route = Screen.Profile.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0

            ProfileScreen(
                id = id,
                onNavigateToProfile = { pokemonId ->
                    navController.navigate(Screen.Profile.createRoute(pokemonId))
                },
                onNavigateBack = navController::popBackStack
            )
        }
    }
}
