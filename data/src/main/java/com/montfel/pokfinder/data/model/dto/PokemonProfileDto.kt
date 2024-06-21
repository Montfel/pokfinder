package com.montfel.pokfinder.data.model.dto

import com.google.gson.annotations.SerializedName

data class PokemonProfileDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("sprites")
    val sprite: SpriteDto? = null,

    @SerializedName("types")
    val types: List<TypeDto>? = null,

    @SerializedName("height")
    val height: Int? = null,

    @SerializedName("weight")
    val weight: Int? = null,

    @SerializedName("base_experience")
    val baseExp: Int? = null,

    @SerializedName("abilities")
    val abilities: List<AbilityDto>? = null,

    @SerializedName("stats")
    val stats: List<StatDto>? = null,
)
