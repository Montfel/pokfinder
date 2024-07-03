package com.montfel.pokfinder.feature.home.ui

sealed interface HomeUiEvent {
    data class NavigateToProfile(val pokemonId: Int) : HomeUiEvent
}
