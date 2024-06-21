package com.montfel.pokfinder.data.model.dto

import com.google.gson.annotations.SerializedName

data class DamageRelationsDto(
    @SerializedName("double_damage_from")
    val doubleDamageFrom: List<DamageTypeDto>? = null,

    @SerializedName("double_damage_to")
    val doubleDamageTo: List<DamageTypeDto>? = null,

    @SerializedName("half_damage_from")
    val halfDamageFrom: List<DamageTypeDto>? = null,

    @SerializedName("half_damage_to")
    val halfDamageTo: List<DamageTypeDto>? = null,

    @SerializedName("no_damage_from")
    val noDamageFrom: List<DamageTypeDto>? = null,

    @SerializedName("no_damage_to")
    val noDamageTo: List<DamageTypeDto>? = null,
)
