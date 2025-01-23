package com.montfel.pokfinder.navigation

sealed class Screen(val route: String) {
    data object Home : Screen(HOME)

    data object Profile : Screen("$PROFILE/{$ID}") {
        fun createRoute(id: Int) = "$PROFILE/$id"
    }

    companion object {
        const val HOME = "home"
        const val PROFILE = "profile"
        const val ID = "id"
    }
}
