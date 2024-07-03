package com.montfel.pokfinder.feature.home.data.di

import com.montfel.pokfinder.feature.home.data.repository.HomeRepositoryImpl
import com.montfel.pokfinder.feature.home.repository.HomeRepository
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
}
