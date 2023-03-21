package com.montfel.pokfinder.presentation.home

import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.home.model.SortOptions
import com.montfel.pokfinder.domain.profile.model.Type

data class HomeUiState(
    val pokemonQuery: String = "",
    val pokemonList: List<PokemonHome> = emptyList(),
    val typeList: List<Type> = emptyList(),
    val generationList: List<Generation> = emptyList(),
    val generationSelected: Int = 0,
    val sortOptionSelected: SortOptions = SortOptions.SmallestNumber,
    val stateOfUi: HomeStateOfUi = HomeStateOfUi.Loading,
)

sealed interface HomeStateOfUi {
    object Loading : HomeStateOfUi
    object Error : HomeStateOfUi
    object Success : HomeStateOfUi
}
