package com.montfel.pokedex.data

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.Pokemon

data class PokemonDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("height")
    val height: Int,
    @SerializedName("name")
    val name: String
) : DtoMapper<Pokemon> {
    override fun toDomain() = Pokemon(
        id = id,
        height = height,
        name = name.replaceFirstChar { it.uppercase() }
    )
}
