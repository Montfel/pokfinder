package com.montfel.pokfinder.feature.home.data.datasource.remote

import com.apollographql.apollo3.api.ApolloResponse
import com.montfel.pokfinder.core.network.FilterPokemonsByGenerationQuery
import com.montfel.pokfinder.core.network.FilterPokemonsByTypesQuery
import com.montfel.pokfinder.core.network.GenerationsQuery
import com.montfel.pokfinder.core.network.PokemonsQuery
import com.montfel.pokfinder.core.network.SearchPokemonsQuery
import com.montfel.pokfinder.core.network.SortPokemonsByIdQuery
import com.montfel.pokfinder.core.network.SortPokemonsByNameQuery
import com.montfel.pokfinder.core.network.TypesQuery
import com.montfel.pokfinder.feature.home.domain.model.OrderType

internal interface HomeService {
    suspend fun getPokemons(limit: Int, offset: Int): ApolloResponse<PokemonsQuery.Data>
    suspend fun searchPokemons(
        limit: Int,
        offset: Int,
        queryName: String = "",
        queryId: Int = 0
    ): ApolloResponse<SearchPokemonsQuery.Data>

    suspend fun filterPokemonsByTypes(
        limit: Int,
        offset: Int,
        types: List<String>,
    ): ApolloResponse<FilterPokemonsByTypesQuery.Data>

    suspend fun filterPokemonsByGeneration(
        limit: Int,
        offset: Int,
        ids: List<Int>,
    ): ApolloResponse<FilterPokemonsByGenerationQuery.Data>

    suspend fun sortPokemonsByName(
        limit: Int,
        offset: Int,
        orderType: OrderType
    ): ApolloResponse<SortPokemonsByNameQuery.Data>

    suspend fun sortPokemonsById(
        limit: Int,
        offset: Int,
        orderType: OrderType
    ): ApolloResponse<SortPokemonsByIdQuery.Data>

    suspend fun getTypes(): ApolloResponse<TypesQuery.Data>
    suspend fun getGenerations(): ApolloResponse<GenerationsQuery.Data>
}
