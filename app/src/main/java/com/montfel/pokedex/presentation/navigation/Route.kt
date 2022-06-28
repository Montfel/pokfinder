package com.montfel.pokedex.presentation.navigation

sealed class Route(val route: String) {
    object Home: Route("home")
    object Profile: Route("profile")
}
