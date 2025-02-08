package com.montfel.pokfinder.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.montfel.pokfinder.core.database.converter.Converters
import com.montfel.pokfinder.core.database.dao.PokemonHomeDao
import com.montfel.pokfinder.core.database.dao.PokemonHomeRemoteKeysDao
import com.montfel.pokfinder.core.database.model.entity.PokemonHomeEntity
import com.montfel.pokfinder.core.database.model.entity.PokemonHomeRemoteKeysEntity

@Database(
    entities = [PokemonHomeEntity::class, PokemonHomeRemoteKeysEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PokfinderDatabase : RoomDatabase() {

    abstract fun pokemonHomeDao(): PokemonHomeDao
    abstract fun pokemonHomeRemoteKeysDao(): PokemonHomeRemoteKeysDao
}
