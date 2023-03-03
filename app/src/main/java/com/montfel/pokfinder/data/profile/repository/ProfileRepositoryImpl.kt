package com.montfel.pokfinder.data.profile.repository

import com.montfel.pokfinder.data.profile.dto.toDomain
import com.montfel.pokfinder.data.profile.mapper.toDomain
import com.montfel.pokfinder.data.profile.service.EvolutionService
import com.montfel.pokfinder.data.profile.service.ProfileService
import com.montfel.pokfinder.domain.profile.model.EvolutionChain
import com.montfel.pokfinder.domain.profile.model.PokemonDamageRelations
import com.montfel.pokfinder.domain.profile.model.PokemonProfile
import com.montfel.pokfinder.domain.profile.model.PokemonSpecies
import com.montfel.pokfinder.domain.profile.repository.ProfileRepository
import com.montfel.pokfinder.helper.Response
import com.montfel.pokfinder.helper.requestWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService,
    private val evolutionService: EvolutionService,
) : ProfileRepository {
    override suspend fun getProfile(pokemonId: String): Response<PokemonProfile> {
        return requestWrapper { profileService.getProfile(pokemonId).toDomain() }
    }

    override suspend fun getSpecies(pokemonId: String): Response<PokemonSpecies> {
        return requestWrapper { profileService.getSpecies(pokemonId).toDomain() }
    }

    override suspend fun getEvolutionChain(evolutionChainId: Int): Response<List<EvolutionChain>> {
        return requestWrapper {
            evolutionService.getEvolutionChain(evolutionChainId).data?.toDomain() ?: emptyList()
        }
    }

    override suspend fun getDamageRelations(typeId: String): Response<PokemonDamageRelations> {
        return requestWrapper { profileService.getDamageRelations(typeId).toDomain() }
    }
}
