package com.montfel.pokfinder.data.di

import com.apollographql.apollo3.ApolloClient
import com.montfel.pokfinder.data.datasource.remote.service.HomeService
import com.montfel.pokfinder.data.datasource.remote.service.ProfileService
import com.montfel.pokfinder.domain.home.usecase.HomeUseCases
import com.montfel.pokfinder.domain.home.usecase.SortPokemonsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourcesModule {

    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeService(apolloClient: ApolloClient) = HomeService(apolloClient)

    @Singleton
    @Provides
    fun provideHomeUseCases() = HomeUseCases(sortPokemonsUseCase = SortPokemonsUseCase())
}
