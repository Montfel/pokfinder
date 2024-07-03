package com.montfel.pokfinder.feature.profile.data.di

import com.montfel.pokfinder.domain.profile.repository.ProfileRepository
import com.montfel.pokfinder.feature.profile.data.repository.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    internal abstract fun bindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository
}
