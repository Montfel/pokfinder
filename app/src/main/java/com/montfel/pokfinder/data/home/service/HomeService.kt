package com.montfel.pokfinder.data.home.service

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.montfel.pokfinder.GenerationsQuery
import com.montfel.pokfinder.PokemonListQuery
import com.montfel.pokfinder.TypesQuery
import javax.inject.Inject

class HomeService @Inject constructor(
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
