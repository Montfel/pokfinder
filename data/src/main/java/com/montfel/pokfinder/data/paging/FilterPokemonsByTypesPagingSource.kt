package com.montfel.pokfinder.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.montfel.pokfinder.data.datasource.remote.service.HomeService
import com.montfel.pokfinder.data.mapper.toPokemonHome
import com.montfel.pokfinder.data.util.Constants
import com.montfel.pokfinder.domain.home.model.PokemonHome

internal class FilterPokemonsByTypesPagingSource(
    private val service: HomeService,
    private val types: List<String>,
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
            val response = service.filterPokemonsByTypes(
                limit = Constants.ITEMS_PER_PAGE,
                offset = offset,
                types = types,
            )
            val pokemons =
                response.data?.pokemon_v2_pokemon?.map { it.toPokemonHome() } ?: emptyList()

            LoadResult.Page(
                data = pokemons,
                prevKey = if (offset == 0) null else offset - Constants.ITEMS_PER_PAGE,
                nextKey = if (pokemons.isEmpty()) null else offset + Constants.ITEMS_PER_PAGE,
            )
        }.getOrElse {
            LoadResult.Error(it)
        }
    }
}