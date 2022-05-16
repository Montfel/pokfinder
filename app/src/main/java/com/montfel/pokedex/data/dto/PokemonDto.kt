package com.montfel.pokedex.data.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.Pokemon

data class PokemonDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("height")
    val height: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprite: SpriteDto,
    @SerializedName("types")
    val types: List<TypesDto>
) : DtoMapper<Pokemon> {
    override fun toDomain() = Pokemon(
        id = id,
        height = height,
        name = name.replaceFirstChar { it.uppercase() },
        image = sprite.other.officialArtwork.frontDefault,
        types = types.map { it.toDomain() }
    )
}

data class SpriteDto(
    @SerializedName("other")
    val other: OtherDto,
)

data class OtherDto(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDto,
)

data class OfficialArtworkDto(
    @SerializedName("front_default")
    val frontDefault: String,
)
