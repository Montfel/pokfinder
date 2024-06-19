package com.montfel.pokfinder.presentation.profile

sealed interface ProfileEvent {
    data class SavePokemonId(val pokemonId: String) : ProfileEvent
    data object FetchPokemonDetails : ProfileEvent
    data class NavigateToProfile(val pokemonId: Int) : ProfileEvent
    data object NavigateBack : ProfileEvent
}
