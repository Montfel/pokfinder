package com.montfel.pokedex.domain.profile.repository

import com.montfel.pokedex.domain.profile.model.PokemonDamageRelations
import com.montfel.pokedex.domain.profile.model.PokemonEvolutionChain
import com.montfel.pokedex.domain.profile.model.PokemonProfile
import com.montfel.pokedex.domain.profile.model.PokemonSpecies
import com.montfel.pokedex.helper.ApiResponse

interface ProfileRepository {
    suspend fun getProfile(pokemonId: String): ApiResponse<PokemonProfile>
    suspend fun getSpecies(pokemonId: String): ApiResponse<PokemonSpecies>
    suspend fun getEvolutionChain(pokemonId: String): ApiResponse<PokemonEvolutionChain>
    suspend fun getDamageRelations(typeId: String): ApiResponse<PokemonDamageRelations>
}
