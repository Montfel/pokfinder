package com.montfel.pokfinder.feature.home.ui

import androidx.paging.PagingData
import com.montfel.pokfinder.core.common.domain.model.Type
import com.montfel.pokfinder.feature.home.domain.model.Generation
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome
import com.montfel.pokfinder.feature.home.domain.model.SortOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUiState(
    val pokemonQuery: String = "",
    val pokemonsPagingDataFlow: Flow<PagingData<PokemonHome>> = emptyFlow(),
    val types: List<Type> = emptyList(),
    val generations: List<Generation> = emptyList(),
    val generationSelected: Int = 0,
    val sortOptionSelected: SortOptions = SortOptions.SmallestNumber,
)
