package com.montfel.pokfinder.data.di

import com.montfel.pokfinder.data.repository.HomeRepositoryImpl
import com.montfel.pokfinder.data.repository.ProfileRepositoryImpl
import com.montfel.pokfinder.domain.home.repository.HomeRepository
import com.montfel.pokfinder.domain.profile.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    internal abstract fun bindPokemonRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    internal abstract fun bindPokemonHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository
}
