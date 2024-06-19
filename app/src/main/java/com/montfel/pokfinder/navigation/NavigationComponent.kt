package com.montfel.pokfinder.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.montfel.pokfinder.presentation.home.HomeScreen
import com.montfel.pokfinder.presentation.profile.ProfileScreen

@ExperimentalMaterialApi
@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeScreen(
                onNavigateToProfile = { id ->
                    navController.navigate(Screen.Profile(id))
                }
            )
        }

        composable<Screen.Profile> {
            val (id) = it.toRoute<Screen.Profile>()

            ProfileScreen(
                id = id.toString(),
                onNavigateToProfile = { profileId ->
                    navController.navigate(Screen.Profile(profileId))
                },
                onNavigateBack = navController::popBackStack
            )
        }
    }
}
