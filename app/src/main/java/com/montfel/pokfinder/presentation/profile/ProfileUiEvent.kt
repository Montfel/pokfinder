package com.montfel.pokfinder.presentation.profile

sealed interface ProfileUiEvent {
    data class NavigateToProfile(val pokemonId: Int) : ProfileUiEvent
    object NavigateBack : ProfileUiEvent
}
