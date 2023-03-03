package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName

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
)
