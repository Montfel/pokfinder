package com.montfel.pokfinder.presentation.home

sealed interface HomeUiEvent {
    data class NavigateToProfile(val pokemonId: Int) : HomeUiEvent
}
