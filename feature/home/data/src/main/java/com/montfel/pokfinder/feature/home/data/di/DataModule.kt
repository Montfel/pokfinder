package com.montfel.pokfinder.feature.home.data.di

import com.montfel.pokfinder.feature.home.data.datasource.remote.HomeServiceImpl
import com.montfel.pokfinder.feature.home.data.datasource.remote.HomeService
import com.montfel.pokfinder.feature.home.data.repository.HomeRepositoryImpl
import com.montfel.pokfinder.feature.home.domain.repository.HomeRepository
import com.montfel.pokfinder.feature.home.domain.usecase.GetPokemonsUseCase
import com.montfel.pokfinder.feature.home.domain.usecase.GetPokemonsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    internal abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    internal abstract fun bindHomeService(
        homeService: HomeServiceImpl
    ): HomeService

    @Binds
    internal abstract fun bindGetPokemonsUseCase(
        getPokemonsUseCaseImpl: GetPokemonsUseCaseImpl
    ): GetPokemonsUseCase
}
