package com.montfel.pokfinder.presentation.profile

import com.montfel.pokfinder.feature.profile.domain.model.EvolutionChain
import com.montfel.pokfinder.feature.profile.domain.model.PokemonProfile
import com.montfel.pokfinder.feature.profile.domain.model.PokemonSpecies

data class ProfileUiState(
    val pokemonId: Int? = null,
    val profile: PokemonProfile? = null,
    val species: PokemonSpecies? = null,
    val strengths: List<String>? = null,
    val weaknesses: List<String>? = null,
    val immunity: List<String>? = null,
    val evolutionChain: List<EvolutionChain>? = null,
    val stateOfUi: ProfileStateOfUi = ProfileStateOfUi.Loading,
)

sealed interface ProfileStateOfUi {
    data object Loading : ProfileStateOfUi
    data object Error : ProfileStateOfUi
    data object Success : ProfileStateOfUi
}
