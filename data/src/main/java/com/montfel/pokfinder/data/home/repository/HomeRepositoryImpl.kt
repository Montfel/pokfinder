package com.montfel.pokfinder.data.home.repository

import com.montfel.pokfinder.data.home.dto.toDomain
import com.montfel.pokfinder.data.home.service.HomeService
import com.montfel.pokfinder.data.util.resultWrapper
import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.home.repository.HomeRepository
import com.montfel.pokfinder.domain.profile.model.Type
import com.montfel.pokfinder.domain.util.ResultType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val service: HomeService
) : HomeRepository {
    override suspend fun getPokemonList(): ResultType<List<PokemonHome>> {
        return resultWrapper {
            service.getPokemonList().data?.pokemon_v2_pokemon?.map { it.toDomain() } ?: emptyList()
        }
    }

    override suspend fun getTypeList(): ResultType<List<Type>> {
        return resultWrapper {
            service.getTypeList().data?.pokemon_v2_type
                ?.map { it.toDomain() }
                ?.filter { it.name != "Unknown" && it.name != "Shadow" }
                ?.sortedBy { it.name }
                ?: emptyList()
        }
    }

    override suspend fun getGenerationList(): ResultType<List<Generation>> {
        return resultWrapper {
            service.getGenerationList().data?.pokemon_v2_generation?.map { it.toDomain() }
                ?: emptyList()
        }
    }
}
