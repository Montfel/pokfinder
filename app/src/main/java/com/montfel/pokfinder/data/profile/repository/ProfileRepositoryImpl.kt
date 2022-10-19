package com.montfel.pokfinder.data.profile.repository

import com.montfel.pokfinder.data.profile.datasource.EvolutionDataSource
import com.montfel.pokfinder.data.profile.datasource.ProfileDataSource
import com.montfel.pokfinder.data.profile.dto.toDomain
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
    private val profileDataSource: ProfileDataSource,
    private val evolutionDataSource: EvolutionDataSource,
) : ProfileRepository {
    override suspend fun getProfile(pokemonId: String): Response<PokemonProfile> {
        return requestWrapper { profileDataSource.getProfile(pokemonId).toDomain() }
    }

    override suspend fun getSpecies(pokemonId: String): Response<PokemonSpecies> {
        return requestWrapper { profileDataSource.getSpecies(pokemonId).toDomain() }
    }

    override suspend fun getEvolutionChain(evolutionChainId: Int): Response<List<EvolutionChain>> {
        return requestWrapper {
            evolutionDataSource.getEvolutionChain(evolutionChainId).data?.toDomain() ?: emptyList()
        }
    }

    override suspend fun getDamageRelations(typeId: String): Response<PokemonDamageRelations> {
        return requestWrapper { profileDataSource.getDamageRelations(typeId).toDomain() }
    }
}
