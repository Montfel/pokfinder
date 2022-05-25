package com.montfel.pokedex.data.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.Pokemon

data class PokemonProfileDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprite: SpriteDto,
    @SerializedName("types")
    val types: List<TypesDto>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
//    @SerializedName("species")
//    val species:

) : DtoMapper<Pokemon> {
    override fun toDomain() = Pokemon(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        image = sprite.other.officialArtwork.frontDefault,
        types = types.map { it.toDomain() },
        height = height,
        weight = weight
    )
}
