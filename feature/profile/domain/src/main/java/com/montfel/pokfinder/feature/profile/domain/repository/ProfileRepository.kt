package com.montfel.pokfinder.feature.profile.domain.repository

import com.montfel.pokfinder.core.common.domain.util.ResultType
import com.montfel.pokfinder.feature.profile.domain.model.EvolutionChain
import com.montfel.pokfinder.feature.profile.domain.model.PokemonDamageRelations
import com.montfel.pokfinder.feature.profile.domain.model.PokemonProfile
import com.montfel.pokfinder.feature.profile.domain.model.PokemonSpecies

interface ProfileRepository {
    suspend fun getProfile(pokemonId: Int): ResultType<PokemonProfile>
    suspend fun getSpecies(pokemonId: Int): ResultType<PokemonSpecies>
    suspend fun getEvolutionChain(evolutionChainId: Int): ResultType<List<EvolutionChain>>
    suspend fun getDamageRelations(typeId: String): ResultType<PokemonDamageRelations>
}
