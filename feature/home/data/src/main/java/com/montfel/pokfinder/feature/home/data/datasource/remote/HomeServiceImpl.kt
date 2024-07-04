package com.montfel.pokfinder.feature.home.data.datasource.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.montfel.pokfinder.core.network.FilterPokemonsByGenerationQuery
import com.montfel.pokfinder.core.network.FilterPokemonsByTypesQuery
import com.montfel.pokfinder.core.network.GenerationsQuery
import com.montfel.pokfinder.core.network.PokemonsQuery
import com.montfel.pokfinder.core.network.SearchPokemonsQuery
import com.montfel.pokfinder.core.network.TypesQuery
import javax.inject.Inject

internal class HomeServiceImpl @Inject constructor(
    private val apolloClient: ApolloClient
): HomeService {
    override suspend fun getPokemons(
        limit: Int,
        offset: Int,
    ): ApolloResponse<PokemonsQuery.Data> {
        return apolloClient.query(
            PokemonsQuery(
                limit = limit,
                offset = offset,
            )
        ).execute()
    }

    override suspend fun searchPokemons(
        limit: Int,
        offset: Int,
        queryName: String,
        queryId: Int
    ): ApolloResponse<SearchPokemonsQuery.Data> {
        return apolloClient.query(
            SearchPokemonsQuery(
                limit = limit,
                offset = offset,
                queryName = queryName,
                queryId = queryId
            )
        ).execute()
    }

    override suspend fun filterPokemonsByTypes(
        limit: Int,
        offset: Int,
        types: List<String>,
    ): ApolloResponse<FilterPokemonsByTypesQuery.Data> {
        return apolloClient.query(
            FilterPokemonsByTypesQuery(
                limit = limit,
                offset = offset,
                types = Optional.present(types),
            )
        ).execute()
    }

    override suspend fun filterPokemonsByGeneration(
        limit: Int,
        offset: Int,
        ids: List<Int>,
    ): ApolloResponse<FilterPokemonsByGenerationQuery.Data> {
        return apolloClient.query(
            FilterPokemonsByGenerationQuery(
                limit = limit,
                offset = offset,
                ids = Optional.present(ids),
            )
        ).execute()
    }

    override suspend fun getTypes(): ApolloResponse<TypesQuery.Data> {
        return apolloClient.query(TypesQuery()).execute()
    }

    override suspend fun getGenerations(): ApolloResponse<GenerationsQuery.Data> {
        return apolloClient.query(GenerationsQuery()).execute()
    }
}
