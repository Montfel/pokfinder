package com.montfel.pokfinder.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.montfel.pokfinder.presentation.home.HomeScreen
import com.montfel.pokfinder.presentation.profile.Profile

@ExperimentalMaterialApi
@Composable
fun NavigationComponent(deviceWidth: Float) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
//        composable(Screen.Splash.route) {
//            Splash(navController = navController)
//        }
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                deviceWidth = deviceWidth
            )
        }
        composable(
            route = Screen.Profile.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            Profile(
                id = it.arguments?.getString("id").orEmpty(),
                navController = navController
            )
        }
    }
}
