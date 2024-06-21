package com.montfel.pokfinder.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.montfel.pokfinder.data.datasource.local.converter.Converters
import com.montfel.pokfinder.data.datasource.local.dao.PokemonHomeDao
import com.montfel.pokfinder.data.datasource.local.dao.PokemonHomeRemoteKeysDao
import com.montfel.pokfinder.data.model.entity.PokemonHomeEntity
import com.montfel.pokfinder.data.model.entity.PokemonHomeRemoteKeysEntity

@Database(
    entities = [PokemonHomeEntity::class, PokemonHomeRemoteKeysEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
internal abstract class PokfinderDatabase : RoomDatabase() {

    abstract fun pokemonHomeDao(): PokemonHomeDao
    abstract fun pokemonHomeRemoteKeysDao(): PokemonHomeRemoteKeysDao
}