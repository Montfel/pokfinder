package com.montfel.pokfinder.di

import com.montfel.pokfinder.data.home.repository.HomeRepositoryImpl
import com.montfel.pokfinder.data.profile.repository.ProfileRepositoryImpl
import com.montfel.pokfinder.domain.home.repository.HomeRepository
import com.montfel.pokfinder.domain.profile.repository.ProfileRepository
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
    internal abstract fun bindsPokemonRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Singleton
    @Binds
    internal abstract fun bindsPokemonHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository
}
