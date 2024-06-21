package com.montfel.pokfinder.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.montfel.pokfinder.data.model.dto.TypeDto
import com.montfel.pokfinder.data.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.POKEMON_HOME_TABLE)
data class PokemonHomeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val types: List<TypeDto>?,
)