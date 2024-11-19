package com.montfel.pokfinder.feature.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.montfel.pokfinder.core.network.FilterPokemonsByGenerationQuery
import com.montfel.pokfinder.feature.home.data.Constants.ITEMS_PER_PAGE
import com.montfel.pokfinder.feature.home.data.datasource.remote.HomeServiceImpl
import com.montfel.pokfinder.feature.home.data.mapper.toPokemonHome
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome

internal class FilterPokemonsByGenerationPagingSource(
    private val service: HomeServiceImpl,
    private val ids: List<Int>,
) : PagingSource<Int, PokemonHome>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonHome>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.inc() ?: anchorPage?.nextKey?.dec()
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonHome> {
        return runCatching {
            val offset = params.key ?: 0
            val response = service.filterPokemonsByGeneration(
                limit = ITEMS_PER_PAGE,
                offset = offset,
                ids = ids,
            )
            val pokemons =
                response.data?.pokemon_v2_pokemon
                    ?.map(FilterPokemonsByGenerationQuery.Pokemon_v2_pokemon::toPokemonHome)
                    ?: emptyList()

            LoadResult.Page(
                data = pokemons,
                prevKey = if (offset == 0) null else offset - ITEMS_PER_PAGE,
                nextKey = if (pokemons.isEmpty()) null else offset + ITEMS_PER_PAGE,
            )
        }.getOrElse {
            LoadResult.Error(it)
        }
    }
}