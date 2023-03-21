package com.montfel.pokfinder.presentation.home

import com.montfel.pokfinder.domain.AssetFromType
import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.SortOptions

sealed interface HomeEvent {
    object LoadHomePage : HomeEvent
    data class SearchPokemon(val query: String) : HomeEvent
    data class FilterByGenaration(val generation: Generation) : HomeEvent
    data class SortPokemonList(val sortOption: SortOptions) : HomeEvent
    data class NavigateToProfile(val pokemonId: Int) : HomeEvent
    data class FilterByType(val typeList: List<AssetFromType>) : HomeEvent
    data class FilterByWeaknesses(val weaknessesList: MutableList<AssetFromType>) : HomeEvent
}
