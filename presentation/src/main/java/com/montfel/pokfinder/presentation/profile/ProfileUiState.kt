package com.montfel.pokfinder.presentation.profile

import com.montfel.pokfinder.domain.profile.model.EvolutionChain
import com.montfel.pokfinder.domain.profile.model.PokemonProfile
import com.montfel.pokfinder.domain.profile.model.PokemonSpecies

data class ProfileUiState(
    val pokemonId: Int? = null,
    val profile: PokemonProfile? = null,
    val species: PokemonSpecies? = null,
    val strengths: List<String> = emptyList(),
    val weaknesses: List<String> = emptyList(),
    val immunity: List<String> = emptyList(),
    val evolutionChain: List<EvolutionChain> = emptyList(),
    val stateOfUi: ProfileStateOfUi = ProfileStateOfUi.Loading,
)

sealed interface ProfileStateOfUi {
    data object Loading : ProfileStateOfUi
    data object Error : ProfileStateOfUi
    data object Success : ProfileStateOfUi
}
