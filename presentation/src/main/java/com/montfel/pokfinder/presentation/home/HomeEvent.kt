package com.montfel.pokfinder.presentation.home

import com.montfel.pokfinder.core.common.domain.model.Type
import com.montfel.pokfinder.feature.home.domain.model.Generation
import com.montfel.pokfinder.feature.home.domain.model.SortOptions

sealed interface HomeEvent {
    data class SearchPokemon(val query: String) : HomeEvent
    data class FilterByGeneration(val generation: Generation) : HomeEvent
    data class SortPokemonList(val sortOption: SortOptions) : HomeEvent
    data class NavigateToProfile(val pokemonId: Int) : HomeEvent
    data class FilterByTypes(val types: List<Type>) : HomeEvent
}
