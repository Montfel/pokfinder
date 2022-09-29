package com.montfel.pokfinder.data.home.repository

import com.montfel.pokfinder.data.home.datasource.HomeDataSource
import com.montfel.pokfinder.data.home.dto.toDomain
import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.home.repository.HomeRepository
import com.montfel.pokfinder.domain.profile.model.Type
import com.montfel.pokfinder.helper.Response
import com.montfel.pokfinder.helper.mapSuccess
import com.montfel.pokfinder.helper.requestWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getPokemonList(): Response<List<PokemonHome>> {
        return requestWrapper { dataSource.getPokemonList() }
            .mapSuccess { data ->
                data.data?.pokemon_v2_pokemon?.map { it.toDomain() } ?: emptyList()
            }
    }

    override suspend fun getTypeList(): Response<List<Type>> {
        return requestWrapper { dataSource.getTypeList() }
            .mapSuccess { data ->
                data.data?.pokemon_v2_type
                    ?.map { it.toDomain() }
                    ?.filter { it.name != "Unknown" && it.name != "Shadow" }
                    ?.sortedBy { it.name }
                    ?: emptyList()
            }
    }

    override suspend fun getGenerationList(): Response<List<Generation>> {
        return requestWrapper { dataSource.getGenerationList() }
            .mapSuccess { data ->
                data.data?.pokemon_v2_generation?.map { it.toDomain() } ?: emptyList()
            }
    }
}
