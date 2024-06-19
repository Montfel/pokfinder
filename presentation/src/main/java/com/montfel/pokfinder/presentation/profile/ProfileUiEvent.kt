package com.montfel.pokfinder.presentation.profile

sealed interface ProfileUiEvent {
    data class NavigateToProfile(val pokemonId: Int) : ProfileUiEvent
    data object NavigateBack : ProfileUiEvent
}
