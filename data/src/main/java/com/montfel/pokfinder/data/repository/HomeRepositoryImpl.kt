package com.montfel.pokfinder.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.montfel.pokfinder.data.datasource.local.database.PokfinderDatabase
import com.montfel.pokfinder.data.datasource.remote.service.HomeService
import com.montfel.pokfinder.data.mapper.toGeneration
import com.montfel.pokfinder.data.mapper.toPokemonHome
import com.montfel.pokfinder.data.mapper.toType
import com.montfel.pokfinder.data.paging.FilterPokemonsByTypesPagingSource
import com.montfel.pokfinder.data.paging.PokemonHomeRemoteMediator
import com.montfel.pokfinder.data.paging.SearchPokemonsPagingSource
import com.montfel.pokfinder.data.util.Constants.ITEMS_PER_PAGE
import com.montfel.pokfinder.data.util.resultWrapper
import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.home.repository.HomeRepository
import com.montfel.pokfinder.domain.profile.model.Type
import com.montfel.pokfinder.domain.util.ResultType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class HomeRepositoryImpl @Inject constructor(
    private val service: HomeService,
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
        delay(5000)
        return resultWrapper {
            service.getGenerations().data?.pokemon_v2_generation?.map { it.toGeneration() }
                ?: emptyList()
        }
    }
}
