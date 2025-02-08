package com.montfel.pokfinder.feature.home.data.di

import com.apollographql.apollo.ApolloClient
import com.montfel.pokfinder.feature.home.data.datasource.remote.HomeServiceImpl
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
    fun provideHomeService(apolloClient: ApolloClient) = HomeServiceImpl(apolloClient)
}
