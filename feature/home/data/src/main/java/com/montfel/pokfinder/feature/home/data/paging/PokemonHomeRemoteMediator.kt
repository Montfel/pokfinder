package com.montfel.pokfinder.feature.home.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.montfel.pokfinder.core.database.PokfinderDatabase
import com.montfel.pokfinder.core.database.model.entity.PokemonHomeEntity
import com.montfel.pokfinder.core.database.model.entity.PokemonHomeRemoteKeysEntity
import com.montfel.pokfinder.core.network.PokemonsQuery
import com.montfel.pokfinder.feature.home.data.Constants.ITEMS_PER_PAGE
import com.montfel.pokfinder.feature.home.data.datasource.remote.HomeServiceImpl
import com.montfel.pokfinder.feature.home.data.mapper.toPokemonHomeEntity

@OptIn(ExperimentalPagingApi::class)
internal class PokemonHomeRemoteMediator(
    private val service: HomeServiceImpl,
    private val pokfinderDatabase: PokfinderDatabase
) : RemoteMediator<Int, PokemonHomeEntity>() {

    private val pokemonHomeDao = pokfinderDatabase.pokemonHomeDao()
    private val pokemonHomeRemoteKeysDao = pokfinderDatabase.pokemonHomeRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonHomeEntity>
    ): MediatorResult {
        return runCatching {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(ITEMS_PER_PAGE) ?: 0
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                }
            }

            val response = service.getPokemons(offset = currentPage, limit = ITEMS_PER_PAGE)
            val pokemons =
                response.data?.pokemon_v2_pokemon
                    ?.map(PokemonsQuery.Pokemon_v2_pokemon::toPokemonHomeEntity)
                    ?: emptyList()
            val endOfPaginationReached = pokemons.isEmpty()

            val prevPage = if (currentPage == 0) null else currentPage - ITEMS_PER_PAGE
            val nextPage = if (endOfPaginationReached) null else currentPage + ITEMS_PER_PAGE

            pokfinderDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokemonHomeDao.clearPokemons()
                    pokemonHomeRemoteKeysDao.clearRemoteKeys()
                }
                val keys = pokemons.map { pokemonHome ->
                    PokemonHomeRemoteKeysEntity(
                        id = pokemonHome.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                pokemonHomeRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                pokemonHomeDao.addPokemons(pokemons = pokemons)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }.getOrElse { exception ->
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PokemonHomeEntity>
    ): PokemonHomeRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                pokemonHomeRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PokemonHomeEntity>
    ): PokemonHomeRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                pokemonHomeRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PokemonHomeEntity>
    ): PokemonHomeRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                pokemonHomeRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }
}
