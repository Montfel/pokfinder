package com.montfel.core.database.di

import android.content.Context
import androidx.room.Room
import com.montfel.core.database.PokfinderDatabase
import com.montfel.core.database.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PokfinderDatabase {
        return Room.databaseBuilder(
            context,
            PokfinderDatabase::class.java,
            Constants.POKFINDER_DATABASE
        ).build()
    }
}
