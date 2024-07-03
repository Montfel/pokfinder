package com.montfel.pokfinder.feature.profile.data.model.dto

import com.google.gson.annotations.SerializedName

data class AbilityDto(
    @SerializedName("ability")
    val abilityName: AbilityNameDto? = null,

    @SerializedName("is_hidden")
    val isHidden: Boolean? = null,

    @SerializedName("slot")
    val slot: Int? = null,
)
