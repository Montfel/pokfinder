package com.montfel.pokedex.data.dto.rest

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.Pokemon

data class PokemonRestDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("sprites")
    val sprite: SpriteDto,

    @SerializedName("types")
    val types: List<TypesRestDto>
) : DtoMapper<Pokemon> {
    override fun toDomain() = Pokemon(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        image = sprite.other.officialArtwork.frontDefault,
        types = types.map { it.toDomain() }
    )
}
