package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.EV
import com.montfel.pokedex.domain.profile.model.PokemonProfile
import com.montfel.pokedex.helper.DtoMapper

data class PokemonProfileDto(
    @SerializedName("id")
    val id: Int,

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

    @SerializedName("base_experience")
    val baseExp: Int,

    @SerializedName("abilities")
    val abilities: List<AbilitiesDto>,

    @SerializedName("stats")
    val stats: List<StatsDto>

) : DtoMapper<PokemonProfile> {
    override fun toDomain() = PokemonProfile(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        image = sprite.other.officialArtwork.frontDefault,
        types = types.map { it.toDomain() },
        height = height.toFloat().div(10),
        weight = weight.toFloat().div(10),
        baseExp = baseExp,
        abilities = abilities.map { it.toDomain() },
        stats = stats.map { it.toDomain() },
        ev = stats
            .filter { it.effort > 0 }
            .map {
                EV(
                    name = it.stat.toDomain().name,
                    effort = it.effort
                )
            }
    )
}
