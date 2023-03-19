package com.montfel.pokfinder.presentation.home

import com.montfel.pokfinder.domain.AssetFromType
import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.profile.model.Type
import com.montfel.pokfinder.presentation.home.bottomsheet.SortOptions

data class HomeUiState(
    val pokemonQuery: String = "",
    val pokemonList: List<PokemonHome> = emptyList(),
    val typeList: List<Type> = emptyList(),
    val generationList: List<Generation> = emptyList(),
    val generationSelected: Int = 0,
    val sortOptionSelected: SortOptions = SortOptions.SmallestNumber,
    val typesSelected: MutableList<AssetFromType> = mutableListOf(),
    val stateOfUi: HomeStateOfUi = HomeStateOfUi.Loading,
)

sealed interface HomeStateOfUi {
    object Loading : HomeStateOfUi
    object Error : HomeStateOfUi
    object Success : HomeStateOfUi
}
