package com.montfel.pokedex.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.montfel.pokedex.presentation.home.Home
import com.montfel.pokedex.presentation.profile.Profile

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
            Home(
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
                id = it.arguments?.getString("id") ?: "",
                navController = navController
            )
        }
    }
}
