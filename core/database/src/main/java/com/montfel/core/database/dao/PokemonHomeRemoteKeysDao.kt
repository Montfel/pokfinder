package com.montfel.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.montfel.core.database.model.entity.PokemonHomeRemoteKeysEntity

@Dao
interface PokemonHomeRemoteKeysDao {

    @Query("SELECT * FROM pokemon_home_remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): PokemonHomeRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<PokemonHomeRemoteKeysEntity>)

    @Query("DELETE FROM pokemon_home_remote_keys_table")
    suspend fun clearRemoteKeys()
}