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
    val height: Float,
    @SerializedName("weight")
    val weight: Float,
    @SerializedName("base_experience")
    val baseExp: Int,
    @SerializedName("abilities")
    val abilities: List<AbilitiesDto>,
    @SerializedName("stats")
    val stats: List<StatsDto>,
    @SerializedName("species")
    val species: SpeciesDto,
    @SerializedName("location_area_encounters")
    val locationUrl: String

) : DtoMapper<Pokemon> {
    override fun toDomain() = Pokemon(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        image = sprite.other.officialArtwork.frontDefault,
        types = types.map { it.toDomain() },
        height = height/10,
        weight = weight/10,
        baseExp = baseExp,
        abilities = abilities,
        stats = stats
    )
}
