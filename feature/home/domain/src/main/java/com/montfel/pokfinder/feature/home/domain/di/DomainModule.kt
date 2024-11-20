package com.montfel.pokfinder.feature.home.domain.di

import com.montfel.pokfinder.feature.home.domain.usecase.GetPokemonsUseCase
import com.montfel.pokfinder.feature.home.domain.usecase.GetPokemonsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainModule {

    @Binds
    internal abstract fun bindGetPokemonsUseCase(
        getPokemonsUseCaseImpl: GetPokemonsUseCaseImpl
    ): GetPokemonsUseCase
}
