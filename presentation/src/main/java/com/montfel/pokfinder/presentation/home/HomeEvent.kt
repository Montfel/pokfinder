package com.montfel.pokfinder.presentation.home

import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.SortOptions
import com.montfel.pokfinder.domain.profile.model.Type

sealed interface HomeEvent {
    data class SearchPokemon(val query: String) : HomeEvent
    data class FilterByGeneration(val generation: Generation) : HomeEvent
    data class SortPokemonList(val sortOption: SortOptions) : HomeEvent
    data class NavigateToProfile(val pokemonId: Int) : HomeEvent
    data class FilterByTypes(val types: List<Type>) : HomeEvent
}
