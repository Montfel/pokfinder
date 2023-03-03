package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName

data class DamageRelationsDto(
    @SerializedName("double_damage_from")
    val doubleDamageFrom: List<DamageTypeDto>,

    @SerializedName("double_damage_to")
    val doubleDamageTo: List<DamageTypeDto>,

    @SerializedName("half_damage_from")
    val halfDamageFrom: List<DamageTypeDto>,

    @SerializedName("half_damage_to")
    val halfDamageTo: List<DamageTypeDto>,

    @SerializedName("no_damage_from")
    val noDamageFrom: List<DamageTypeDto>,

    @SerializedName("no_damage_to")
    val noDamageTo: List<DamageTypeDto>
)
