package com.montfel.pokedex.data.home.datasource

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.montfel.pokedex.GenerationsQuery
import com.montfel.pokedex.PokemonListQuery
import com.montfel.pokedex.TypesQuery
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val apolloClient: ApolloClient
) {
    suspend fun getPokemonList(): ApolloResponse<PokemonListQuery.Data> {
        return apolloClient.query(PokemonListQuery()).execute()
    }

    suspend fun getTypeList(): ApolloResponse<TypesQuery.Data> {
        return apolloClient.query(TypesQuery()).execute()
    }

    suspend fun getGenerationList(): ApolloResponse<GenerationsQuery.Data> {
        return apolloClient.query(GenerationsQuery()).execute()
    }
}
