package com.montfel.pokfinder.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data class Profile(
        val id: Int
    ) : Screen
}
