package com.montfel.pokedex.data.profile.datasource

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.montfel.pokedex.EvolutionQuery
import javax.inject.Inject

class EvolutionDataSource @Inject constructor(
    private val apolloClient: ApolloClient
) {
    suspend fun getEvolutionChain(evolutionChainId: Int): ApolloResponse<EvolutionQuery.Data> {
        return apolloClient.query(EvolutionQuery(evolutionChainId)).execute()
    }
}
