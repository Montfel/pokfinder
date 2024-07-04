package com.montfel.pokfinder.feature.home.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.montfel.core.database.PokfinderDatabase
import com.montfel.network.util.resultWrapper
import com.montfel.pokfinder.core.common.domain.model.Type
import com.montfel.pokfinder.core.common.domain.util.ResultType
import com.montfel.pokfinder.feature.home.data.Constants.ITEMS_PER_PAGE
import com.montfel.pokfinder.feature.home.data.datasource.remote.HomeServiceImpl
import com.montfel.pokfinder.feature.home.data.mapper.toGeneration
import com.montfel.pokfinder.feature.home.data.mapper.toPokemonHome
import com.montfel.pokfinder.feature.home.data.mapper.toType
import com.montfel.pokfinder.feature.home.data.paging.FilterPokemonsByGenerationPagingSource
import com.montfel.pokfinder.feature.home.data.paging.FilterPokemonsByTypesPagingSource
import com.montfel.pokfinder.feature.home.data.paging.PokemonHomeRemoteMediator
import com.montfel.pokfinder.feature.home.data.paging.SearchPokemonsPagingSource
import com.montfel.pokfinder.feature.home.domain.model.Generation
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome
import com.montfel.pokfinder.feature.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class HomeRepositoryImpl @Inject constructor(
    private val service: HomeServiceImpl,
    private val pokfinderDatabase: PokfinderDatabase,
) : HomeRepository {

    override fun getPokemons(): Flow<PagingData<PokemonHome>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = PokemonHomeRemoteMediator(
                service = service,
                pokfinderDatabase = pokfinderDatabase
            ),
            pagingSourceFactory = { pokfinderDatabase.pokemonHomeDao().getAllPokemons() }
        ).flow.map { pagingData ->
            pagingData.map { it.toPokemonHome() }
        }
    }

    override fun searchPokemons(queryName: String, queryId: Int): Flow<PagingData<PokemonHome>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPokemonsPagingSource(
                    service = service,
                    queryName = queryName,
                    queryId = queryId
                )
            }
        ).flow
    }

    override fun filterPokemonsByTypes(types: List<String>): Flow<PagingData<PokemonHome>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                FilterPokemonsByTypesPagingSource(
                    service = service,
                    types = types
                )
            }
        ).flow
    }

    override fun filterPokemonsByGeneration(ids: List<Int>): Flow<PagingData<PokemonHome>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                FilterPokemonsByGenerationPagingSource(
                    service = service,
                    ids = ids
                )
            }
        ).flow
    }

    override suspend fun getTypes(): ResultType<List<Type>> {
        return resultWrapper {
            service.getTypes().data?.pokemon_v2_type
                ?.map { it.toType() }
                ?.filter { it.name != "Unknown" && it.name != "Shadow" }
                ?.sortedBy { it.name }
                ?: emptyList()
        }
    }

    override suspend fun getGenerations(): ResultType<List<Generation>> {
        return resultWrapper {
            service.getGenerations().data?.pokemon_v2_generation?.map { it.toGeneration() }
                ?: emptyList()
        }
    }
}
