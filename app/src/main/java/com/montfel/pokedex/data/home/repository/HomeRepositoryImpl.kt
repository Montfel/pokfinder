package com.montfel.pokedex.data.home.repository

import com.montfel.pokedex.data.home.datasource.HomeDataSource
import com.montfel.pokedex.data.home.dto.toDomain
import com.montfel.pokedex.domain.home.model.Generation
import com.montfel.pokedex.domain.home.model.PokemonHome
import com.montfel.pokedex.domain.home.repository.HomeRepository
import com.montfel.pokedex.domain.profile.model.Type
import com.montfel.pokedex.helper.Response
import com.montfel.pokedex.helper.mapSuccess
import com.montfel.pokedex.helper.requestWrapper
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
