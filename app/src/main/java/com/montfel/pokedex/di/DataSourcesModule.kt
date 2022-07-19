package com.montfel.pokedex.di

import com.montfel.pokedex.data.datasource.PokemonDataSource
import com.montfel.pokedex.domain.usecase.HomeUseCases
import com.montfel.pokedex.domain.usecase.SortPokemonsUseCase
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
    fun providesPokemonDataSource(retrofit: Retrofit): PokemonDataSource =
        retrofit.create(PokemonDataSource::class.java)

    @Provides
    @Singleton
    fun provideHomeUseCases() : HomeUseCases {
        return HomeUseCases(
            sortPokemonsUseCase = SortPokemonsUseCase()
        )
    }
}
