package com.montfel.pokedex.di

import com.apollographql.apollo3.ApolloClient
import com.montfel.pokedex.data.profile.datasource.ProfileDataSource
import com.montfel.pokedex.data.home.datasource.HomeDataSource
import com.montfel.pokedex.domain.home.usecase.HomeUseCases
import com.montfel.pokedex.domain.home.usecase.SortPokemonsUseCase
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
    fun providesProfileDataSource(retrofit: Retrofit): ProfileDataSource =
        retrofit.create(ProfileDataSource::class.java)

    @Singleton
    @Provides
    fun providesHomeDataSource(apolloClient: ApolloClient) = HomeDataSource(apolloClient)

    @Provides
    @Singleton
    fun providesHomeUseCases() = HomeUseCases(sortPokemonsUseCase = SortPokemonsUseCase())
}
