package com.montfel.pokfinder.feature.profile.ui

sealed interface ProfileEvent {
    data class SavePokemonId(val pokemonId: Int) : ProfileEvent
    data object FetchPokemonDetails : ProfileEvent
    data class NavigateToProfile(val pokemonId: Int) : ProfileEvent
    data object NavigateBack : ProfileEvent
}
