package com.montfel.pokfinder.feature.profile.data.di

import com.montfel.pokfinder.feature.profile.data.datasource.remote.service.ProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourcesModule {

    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): com.montfel.pokfinder.feature.profile.data.datasource.remote.service.ProfileService {
        return retrofit.create(com.montfel.pokfinder.feature.profile.data.datasource.remote.service.ProfileService::class.java)
    }
}
