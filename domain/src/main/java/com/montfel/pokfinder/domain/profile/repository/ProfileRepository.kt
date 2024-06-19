package com.montfel.pokfinder.domain.profile.repository

import com.montfel.pokfinder.domain.profile.model.EvolutionChain
import com.montfel.pokfinder.domain.profile.model.PokemonDamageRelations
import com.montfel.pokfinder.domain.profile.model.PokemonProfile
import com.montfel.pokfinder.domain.profile.model.PokemonSpecies
import com.montfel.pokfinder.domain.util.ResultType

interface ProfileRepository {
    suspend fun getProfile(pokemonId: String): ResultType<PokemonProfile>
    suspend fun getSpecies(pokemonId: String): ResultType<PokemonSpecies>
    suspend fun getEvolutionChain(evolutionChainId: Int): ResultType<List<EvolutionChain>>
    suspend fun getDamageRelations(typeId: String): ResultType<PokemonDamageRelations>
}
