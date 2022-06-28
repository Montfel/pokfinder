package com.montfel.pokedex.data.datasource

import com.apollographql.apollo3.ApolloClient

private const val BASE_URL = "https://beta.pokeapi.co/graphql/v1beta"

val apolloClient = ApolloClient
    .Builder()
    .serverUrl(BASE_URL)
    .build()
