package com.montfel.pokedex.domain.profile.repository

import com.montfel.pokedex.domain.profile.model.PokemonEvolutionChain
import com.montfel.pokedex.domain.profile.model.PokemonProfile
import com.montfel.pokedex.domain.profile.model.PokemonSpecies
import com.montfel.pokedex.helper.ApiResponse

interface ProfileRepository {
    suspend fun getPokemonProfile(pokemonId: String): ApiResponse<PokemonProfile>
    suspend fun getPokemonSpecies(pokemonId: String): ApiResponse<PokemonSpecies>
    suspend fun getPokemonEvolutionChain(pokemonId: String): ApiResponse<PokemonEvolutionChain>
}
