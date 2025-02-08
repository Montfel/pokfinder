package com.montfel.pokfinder.feature.profile.data.datasource.remote.service

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.montfel.pokfinder.core.network.EvolutionQuery
import javax.inject.Inject

internal class EvolutionService @Inject constructor(
    private val apolloClient: ApolloClient
) {
    suspend fun getEvolutionChain(evolutionChainId: Int): ApolloResponse<EvolutionQuery.Data> {
        return apolloClient.query(EvolutionQuery(evolutionChainId)).execute()
    }
}
