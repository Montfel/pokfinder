package com.montfel.pokfinder.data.model.dto

import com.google.gson.annotations.SerializedName

data class PokemonDamageRelationsDto(
    @SerializedName("damage_relations")
    val damageRelations: DamageRelationsDto? = null,
)
