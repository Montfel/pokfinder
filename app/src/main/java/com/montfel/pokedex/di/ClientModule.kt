package com.montfel.pokedex.di

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClientModule {
    private const val RETROFIT_BASE_URL = "https://pokeapi.co/api/v2/"
    private const val APOLLO_BASE_URL = "https://beta.pokeapi.co/graphql/v1beta"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(RETROFIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providesApolloClient(): ApolloClient =
        ApolloClient
            .Builder()
            .serverUrl(APOLLO_BASE_URL)
            .build()
}
