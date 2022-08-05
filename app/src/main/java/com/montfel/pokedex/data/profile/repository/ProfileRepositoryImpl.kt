package com.montfel.pokedex.data.profile.repository

import com.montfel.pokedex.data.profile.datasource.ProfileDataSource
import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.domain.profile.repository.ProfileRepository
import com.montfel.pokedex.helper.ApiResponse
import com.montfel.pokedex.helper.requestWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val dataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getPokemon(pokemonName: String): ApiResponse<Pokemon> {
        return requestWrapper { dataSource.getPokemon(pokemonName).toDomain() }
    }
}
