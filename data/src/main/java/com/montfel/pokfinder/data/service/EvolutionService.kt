package com.montfel.pokfinder.data.service

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.montfel.pokfinder.data.EvolutionQuery
import javax.inject.Inject

class EvolutionService @Inject constructor(
    private val apolloClient: ApolloClient
) {
    suspend fun getEvolutionChain(evolutionChainId: Int): ApolloResponse<EvolutionQuery.Data> {
        return apolloClient.query(EvolutionQuery(evolutionChainId)).execute()
    }
}
