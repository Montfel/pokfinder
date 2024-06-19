package com.montfel.pokfinder.data.di

import com.apollographql.apollo3.ApolloClient
import com.montfel.pokfinder.data.home.service.HomeService
import com.montfel.pokfinder.data.profile.service.ProfileService
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

    @Singleton
    @Provides
    fun provideProfileService(retrofit: Retrofit): ProfileService =
        retrofit.create(ProfileService::class.java)

    @Singleton
    @Provides
    fun provideHomeService(apolloClient: ApolloClient) = HomeService(apolloClient)

    @Provides
    @Singleton
    fun provideHomeUseCases() = HomeUseCases(sortPokemonsUseCase = SortPokemonsUseCase())
}
