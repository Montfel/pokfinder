package com.montfel.pokfinder.presentation.home

import com.montfel.pokfinder.designsystem.model.AssetFromType
import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.SortOptions

sealed interface HomeEvent {
    data object LoadHomePage : HomeEvent
    data class SearchPokemon(val query: String) : HomeEvent
    data class FilterByGeneration(val generation: Generation) : HomeEvent
    data class SortPokemonList(val sortOption: SortOptions) : HomeEvent
    data class NavigateToProfile(val pokemonId: Int) : HomeEvent
    data class FilterByType(val typeList: List<AssetFromType>) : HomeEvent
    data class FilterByWeaknesses(val weaknessesList: List<AssetFromType>) : HomeEvent
}
