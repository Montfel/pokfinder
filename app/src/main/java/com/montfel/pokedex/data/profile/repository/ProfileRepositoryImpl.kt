package com.montfel.pokedex.data.profile.repository

import com.montfel.pokedex.data.profile.datasource.EvolutionDataSource
import com.montfel.pokedex.data.profile.datasource.ProfileDataSource
import com.montfel.pokedex.data.profile.dto.toDomain
import com.montfel.pokedex.domain.profile.model.EvolutionChain
import com.montfel.pokedex.domain.profile.model.PokemonDamageRelations
import com.montfel.pokedex.domain.profile.model.PokemonProfile
import com.montfel.pokedex.domain.profile.model.PokemonSpecies
import com.montfel.pokedex.domain.profile.repository.ProfileRepository
import com.montfel.pokedex.helper.Response
import com.montfel.pokedex.helper.requestWrapper
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
