package com.montfel.pokfinder.presentation.home

import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.home.model.SortOptions
import com.montfel.pokfinder.domain.profile.model.Type

data class HomeUiState(
    val pokemonQuery: String = "",
    val pokemons: List<PokemonHome> = emptyList(),
    val types: List<Type> = emptyList(),
    val generations: List<Generation> = emptyList(),
    val generationSelected: Int = 0,
    val sortOptionSelected: SortOptions = SortOptions.SmallestNumber,
    val stateOfUi: HomeStateOfUi = HomeStateOfUi.Loading,
)

sealed interface HomeStateOfUi {
    data object Loading : HomeStateOfUi
    data object Error : HomeStateOfUi
    data object Success : HomeStateOfUi
}
