package com.montfel.pokedex.data.profile.repository

import com.montfel.pokedex.data.profile.datasource.ProfileDataSource
import com.montfel.pokedex.domain.profile.model.PokemonDamageRelations
import com.montfel.pokedex.domain.profile.model.PokemonEvolutionChain
import com.montfel.pokedex.domain.profile.model.PokemonProfile
import com.montfel.pokedex.domain.profile.model.PokemonSpecies
import com.montfel.pokedex.domain.profile.repository.ProfileRepository
import com.montfel.pokedex.helper.Response
import com.montfel.pokedex.helper.requestWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val dataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getProfile(pokemonId: String): Response<PokemonProfile> {
        return requestWrapper { dataSource.getProfile(pokemonId).toDomain() }
    }

    override suspend fun getSpecies(pokemonId: String): Response<PokemonSpecies> {
        return requestWrapper { dataSource.getSpecies(pokemonId).toDomain() }
    }

    override suspend fun getEvolutionChain(pokemonId: String): Response<PokemonEvolutionChain> {
        return requestWrapper { dataSource.getEvolutionChain(pokemonId).toDomain() }
    }

    override suspend fun getDamageRelations(typeId: String): Response<PokemonDamageRelations> {
        return requestWrapper { dataSource.getDamageRelations(typeId).toDomain() }
    }
}
