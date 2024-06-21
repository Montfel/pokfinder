package com.montfel.pokfinder.data.datasource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.montfel.pokfinder.data.model.entity.PokemonHomeEntity

@Dao
internal interface PokemonHomeDao {

    @Query("SELECT * FROM pokemon_home_table")
    fun getAllPokemons(): PagingSource<Int, PokemonHomeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPokemons(pokemons: List<PokemonHomeEntity>)

    @Query("DELETE FROM pokemon_home_table")
    suspend fun clearPokemons()
}