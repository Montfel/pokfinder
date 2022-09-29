package com.montfel.pokfinder.domain.profile.repository

import com.montfel.pokfinder.domain.profile.model.EvolutionChain
import com.montfel.pokfinder.domain.profile.model.PokemonDamageRelations
import com.montfel.pokfinder.domain.profile.model.PokemonProfile
import com.montfel.pokfinder.domain.profile.model.PokemonSpecies
import com.montfel.pokfinder.helper.Response

interface ProfileRepository {
    suspend fun getProfile(pokemonId: String): Response<PokemonProfile>
    suspend fun getSpecies(pokemonId: String): Response<PokemonSpecies>
    suspend fun getEvolutionChain(evolutionChainId: Int): Response<List<EvolutionChain>>
    suspend fun getDamageRelations(typeId: String): Response<PokemonDamageRelations>
}
