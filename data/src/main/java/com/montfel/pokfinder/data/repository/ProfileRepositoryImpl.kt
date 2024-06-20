package com.montfel.pokfinder.data.repository

import com.montfel.pokfinder.data.mapper.toDomain
import com.montfel.pokfinder.data.service.EvolutionService
import com.montfel.pokfinder.data.service.ProfileService
import com.montfel.pokfinder.data.util.resultWrapper
import com.montfel.pokfinder.domain.profile.model.EvolutionChain
import com.montfel.pokfinder.domain.profile.model.PokemonDamageRelations
import com.montfel.pokfinder.domain.profile.model.PokemonProfile
import com.montfel.pokfinder.domain.profile.model.PokemonSpecies
import com.montfel.pokfinder.domain.profile.repository.ProfileRepository
import com.montfel.pokfinder.domain.util.ResultType
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService,
    private val evolutionService: EvolutionService,
) : ProfileRepository {
    override suspend fun getProfile(pokemonId: Int): ResultType<PokemonProfile> {
        return resultWrapper { profileService.getProfile(pokemonId).toDomain() }
    }

    override suspend fun getSpecies(pokemonId: Int): ResultType<PokemonSpecies> {
        return resultWrapper { profileService.getSpecies(pokemonId).toDomain() }
    }

    override suspend fun getEvolutionChain(evolutionChainId: Int): ResultType<List<EvolutionChain>> {
        return resultWrapper {
            evolutionService.getEvolutionChain(evolutionChainId).data?.toDomain() ?: emptyList()
        }
    }

    override suspend fun getDamageRelations(typeId: String): ResultType<PokemonDamageRelations> {
        return resultWrapper { profileService.getDamageRelations(typeId).toDomain() }
    }
}
