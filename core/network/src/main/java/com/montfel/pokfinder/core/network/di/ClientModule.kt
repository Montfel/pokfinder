package com.montfel.pokfinder.core.network.di

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ClientModule {
    private const val RETROFIT_BASE_URL = "https://pokeapi.co/api/v2/"
    private const val APOLLO_BASE_URL = "https://beta.pokeapi.co/graphql/v1beta"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(RETROFIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient
            .Builder()
            .serverUrl(APOLLO_BASE_URL)
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }
}
