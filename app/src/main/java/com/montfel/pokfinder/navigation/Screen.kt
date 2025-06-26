package com.montfel.pokfinder.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {

    @Serializable
    data object Home : Screen

    @Serializable
    data class Profile(
        val id: Int
    ) : Screen
}
