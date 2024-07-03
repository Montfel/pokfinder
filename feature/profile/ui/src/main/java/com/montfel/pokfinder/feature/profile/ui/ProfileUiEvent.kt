package com.montfel.pokfinder.feature.profile.ui

sealed interface ProfileUiEvent {
    data class NavigateToProfile(val pokemonId: Int) : ProfileUiEvent
    data object NavigateBack : ProfileUiEvent
}
