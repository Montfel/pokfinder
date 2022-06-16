package com.montfel.pokedex.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.montfel.pokedex.presentation.home.Home
import com.montfel.pokedex.presentation.navigation.Route
import com.montfel.pokedex.presentation.profile.Profile

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.Home.route) {
        composable(Route.Home.route) {
            Home(navController = navController)
        }
        composable("profile/{id}") {
            Profile(
                id = it.arguments?.getString("id")!!,
                navController = navController
            )
        }
    }
}
