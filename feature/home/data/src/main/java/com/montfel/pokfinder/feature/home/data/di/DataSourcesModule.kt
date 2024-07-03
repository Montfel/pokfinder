package com.montfel.pokfinder.feature.home.data.di

import com.apollographql.apollo3.ApolloClient
import com.montfel.pokfinder.feature.home.usecase.HomeUseCases
import com.montfel.pokfinder.feature.home.usecase.SortPokemonsUseCase
import com.montfel.pokfinder.feature.home.data.datasource.remote.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourcesModule {

    @Provides
    @Singleton
    fun provideHomeService(apolloClient: ApolloClient) = HomeService(apolloClient)

    @Singleton
    @Provides
    fun provideHomeUseCases() =
        com.montfel.pokfinder.feature.home.usecase.HomeUseCases(sortPokemonsUseCase = com.montfel.pokfinder.feature.home.usecase.SortPokemonsUseCase())
}
