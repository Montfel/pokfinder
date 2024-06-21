package com.montfel.pokfinder.presentation.home

import androidx.paging.PagingData
import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.home.model.SortOptions
import com.montfel.pokfinder.domain.profile.model.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUiState(
    val pokemonQuery: String = "",
    val pokemons: List<PokemonHome> = emptyList(),
    val pokemonsPagingDataFlow: Flow<PagingData<PokemonHome>> = emptyFlow(),
    val types: List<Type> = emptyList(),
    val generations: List<Generation> = emptyList(),
    val generationSelected: Int = 0,
    val sortOptionSelected: SortOptions = SortOptions.SmallestNumber,
)
