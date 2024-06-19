package com.montfel.pokfinder.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")

    data object Profile : Screen("profile/{id}") {
        fun createRoute(id: Int) = "profile/$id"
    }
}