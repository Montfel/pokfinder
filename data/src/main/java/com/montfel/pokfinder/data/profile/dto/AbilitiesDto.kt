package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName

data class AbilitiesDto(
    @SerializedName("ability")
    val ability: AbilityDto,

    @SerializedName("is_hidden")
    val isHidden: Boolean,

    @SerializedName("slot")
    val slot: Int
)
