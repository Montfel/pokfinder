package com.montfel.pokfinder.data.dto.profile

import com.google.gson.annotations.SerializedName

data class AbilitiesDto(
    @SerializedName("ability")
    val ability: AbilityDto? = null,

    @SerializedName("is_hidden")
    val isHidden: Boolean? = null,

    @SerializedName("slot")
    val slot: Int? = null,
)
