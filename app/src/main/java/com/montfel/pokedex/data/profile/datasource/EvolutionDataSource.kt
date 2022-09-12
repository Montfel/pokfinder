package com.montfel.pokedex.data.profile.datasource

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.montfel.pokedex.EvolutionQuery
import com.montfel.pokedex.GenerationsQuery
import com.montfel.pokedex.PokemonListQuery
import com.montfel.pokedex.TypesQuery
import com.montfel.pokedex.data.profile.dto.PokemonDamageRelationsDto
import com.montfel.pokedex.data.profile.dto.PokemonProfileDto
import com.montfel.pokedex.data.profile.dto.PokemonSpeciesDto
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

class EvolutionDataSource @Inject constructor(
    private val apolloClient: ApolloClient
) {
    suspend fun getEvolutionChain(evolutionChainId: Int): ApolloResponse<EvolutionQuery.Data> {
        return apolloClient.query(EvolutionQuery(evolutionChainId)).execute()
    }
}
