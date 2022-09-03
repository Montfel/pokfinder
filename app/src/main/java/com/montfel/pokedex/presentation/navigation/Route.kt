package com.montfel.pokedex.presentation.navigation

sealed class Screen(val route: String) {
//    object Splash: Screen("splash")

    object Home : Screen("home")

    object Profile : Screen("profile/{id}") {
        fun createRoute(id: Int) = "profile/$id"
    }
}
