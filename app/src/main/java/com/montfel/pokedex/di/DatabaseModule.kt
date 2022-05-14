package com.montfel.pokedex.di

import com.montfel.pokedex.data.PokemonRepositoryImpl
import com.montfel.pokedex.domain.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseModule {

    @Binds
    internal abstract fun bindPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository
}
