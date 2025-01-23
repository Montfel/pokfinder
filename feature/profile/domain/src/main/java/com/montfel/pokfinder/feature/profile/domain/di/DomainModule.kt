package com.montfel.pokfinder.feature.profile.domain.di

import com.montfel.pokfinder.feature.profile.domain.usecase.GetProfileUseCase
import com.montfel.pokfinder.feature.profile.domain.usecase.GetProfileUseCaseImpl
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.Module
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainModule {

    @Binds
    internal abstract fun bindGetProfileUseCase(
        getProfileUseCaseImpl: GetProfileUseCaseImpl
    ): GetProfileUseCase
}
