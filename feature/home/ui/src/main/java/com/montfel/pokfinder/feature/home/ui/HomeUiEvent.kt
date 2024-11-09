package com.montfel.pokfinder.feature.home.ui

sealed interface HomeUiEvent {
    data class NavigateToProfile(val pokemonId: Int) : HomeUiEvent
    data class NavigateToDeepLink(val deepLink: String) : HomeUiEvent
}
