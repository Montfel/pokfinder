package com.montfel.pokfinder.feature.profile.data.model.dto

import com.google.gson.annotations.SerializedName

data class PokemonDamageRelationsDto(
    @SerializedName("damage_relations")
    val damageRelations: DamageRelationsDto? = null,
)
