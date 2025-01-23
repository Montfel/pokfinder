package com.montfel.core.database.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.montfel.core.database.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.POKEMON_HOME_REMOTE_KEYS_TABLE)
data class PokemonHomeRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
)
