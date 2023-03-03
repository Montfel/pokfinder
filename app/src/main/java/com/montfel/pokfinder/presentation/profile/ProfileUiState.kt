package com.montfel.pokfinder.presentation.profile

import com.montfel.pokfinder.domain.profile.model.EvolutionChain
import com.montfel.pokfinder.domain.profile.model.PokemonProfile
import com.montfel.pokfinder.domain.profile.model.PokemonSpecies

data class ProfileUiState(
    val profile: PokemonProfile? = null,
    val species: PokemonSpecies? = null,
    val strengths: List<String> = emptyList(),
    val weaknesses: List<String> = emptyList(),
    val immunity: List<String> = emptyList(),
    val evolutionChain: List<EvolutionChain> = emptyList(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)
