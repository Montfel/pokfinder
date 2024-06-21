package com.montfel.pokfinder.data.repository

import com.montfel.pokfinder.data.mapper.toPokemonSpecies
import com.montfel.pokfinder.data.mapper.toPokemonProfile
import com.montfel.pokfinder.data.mapper.toPokemonDamageRelations
import com.montfel.pokfinder.data.mapper.toEvolutionChains
import com.montfel.pokfinder.data.datasource.remote.service.EvolutionService
import com.montfel.pokfinder.data.datasource.remote.service.ProfileService
import com.montfel.pokfinder.data.util.resultWrapper
import com.montfel.pokfinder.domain.profile.model.EvolutionChain
import com.montfel.pokfinder.domain.profile.model.PokemonDamageRelations
import com.montfel.pokfinder.domain.profile.model.PokemonProfile
import com.montfel.pokfinder.domain.profile.model.PokemonSpecies
import com.montfel.pokfinder.domain.profile.repository.ProfileRepository
import com.montfel.pokfinder.domain.util.ResultType
import javax.inject.Inject

internal class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService,
    private val evolutionService: EvolutionService,
) : ProfileRepository {
    override suspend fun getProfile(pokemonId: Int): ResultType<PokemonProfile> {
        return resultWrapper { profileService.getProfile(pokemonId).toPokemonProfile() }
    }

    override suspend fun getSpecies(pokemonId: Int): ResultType<PokemonSpecies> {
        return resultWrapper { profileService.getSpecies(pokemonId).toPokemonSpecies() }
    }

    override suspend fun getEvolutionChain(evolutionChainId: Int): ResultType<List<EvolutionChain>> {
        return resultWrapper {
            evolutionService.getEvolutionChain(evolutionChainId).data?.toEvolutionChains() ?: emptyList()
        }
    }

    override suspend fun getDamageRelations(typeId: String): ResultType<PokemonDamageRelations> {
        return resultWrapper { profileService.getDamageRelations(typeId).toPokemonDamageRelations() }
    }
}
