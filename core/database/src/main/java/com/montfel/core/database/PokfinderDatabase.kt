package com.montfel.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.montfel.core.database.converter.Converters
import com.montfel.core.database.dao.PokemonHomeDao
import com.montfel.core.database.dao.PokemonHomeRemoteKeysDao
import com.montfel.core.database.model.entity.PokemonHomeEntity
import com.montfel.core.database.model.entity.PokemonHomeRemoteKeysEntity

@Database(
    entities = [PokemonHomeEntity::class, PokemonHomeRemoteKeysEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PokfinderDatabase : RoomDatabase() {

    abstract fun pokemonHomeDao(): PokemonHomeDao
    abstract fun pokemonHomeRemoteKeysDao(): PokemonHomeRemoteKeysDao
}