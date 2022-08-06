package com.montfel.pokedex.data.profile.repository

import com.montfel.pokedex.data.profile.datasource.ProfileDataSource
import com.montfel.pokedex.domain.profile.model.PokemonEvolutionChain
import com.montfel.pokedex.domain.profile.model.PokemonProfile
import com.montfel.pokedex.domain.profile.model.PokemonSpecies
import com.montfel.pokedex.domain.profile.repository.ProfileRepository
import com.montfel.pokedex.helper.ApiResponse
import com.montfel.pokedex.helper.requestWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val dataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getPokemonProfile(pokemonId: String): ApiResponse<PokemonProfile> {
        return requestWrapper { dataSource.getPokemonProfile(pokemonId).toDomain() }
    }

    override suspend fun getPokemonSpecies(pokemonId: String): ApiResponse<PokemonSpecies> {
        return requestWrapper { dataSource.getPokemonSpecies(pokemonId).toDomain() }
    }

    override suspend fun getPokemonEvolutionChain(pokemonId: String): ApiResponse<PokemonEvolutionChain> {
        return requestWrapper { dataSource.getPokemonEvolutionChain(pokemonId).toDomain() }
    }
}
