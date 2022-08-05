package com.montfel.pokedex.di

import com.montfel.pokedex.data.home.repository.HomeRepositoryImpl
import com.montfel.pokedex.data.profile.repository.ProfileRepositoryImpl
import com.montfel.pokedex.domain.home.repository.HomeRepository
import com.montfel.pokedex.domain.profile.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Singleton
    @Binds
    internal abstract fun bindPokemonRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Singleton
    @Binds
    internal abstract fun bindPokemonHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository
}
