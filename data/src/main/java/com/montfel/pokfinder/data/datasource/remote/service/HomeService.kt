package com.montfel.pokfinder.data.datasource.remote.service

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.montfel.pokfinder.data.FilterPokemonsByTypesQuery
import com.montfel.pokfinder.data.GenerationsQuery
import com.montfel.pokfinder.data.PokemonsQuery
import com.montfel.pokfinder.data.SearchPokemonsQuery
import com.montfel.pokfinder.data.TypesQuery
import javax.inject.Inject

internal class HomeService @Inject constructor(
    private val apolloClient: ApolloClient
) {
    suspend fun getPokemons(
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

    suspend fun searchPokemons(
        limit: Int,
        offset: Int,
        queryName: String = "",
        queryId: Int = 0
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

    suspend fun filterPokemonsByTypes(
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

    suspend fun getTypes(): ApolloResponse<TypesQuery.Data> {
        return apolloClient.query(TypesQuery()).execute()
    }

    suspend fun getGenerations(): ApolloResponse<GenerationsQuery.Data> {
        return apolloClient.query(GenerationsQuery()).execute()
    }
}
