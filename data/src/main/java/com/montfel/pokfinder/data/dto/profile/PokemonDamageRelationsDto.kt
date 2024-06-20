package com.montfel.pokfinder.data.dto.profile

import com.google.gson.annotations.SerializedName

data class PokemonDamageRelationsDto(
    @SerializedName("damage_relations")
    val damageRelations: DamageRelationsDto? = null,
)
