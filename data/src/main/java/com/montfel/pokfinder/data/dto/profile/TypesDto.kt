package com.montfel.pokfinder.data.dto.profile

import com.google.gson.annotations.SerializedName

data class TypesDto(
    @SerializedName("slot")
    val slot: Int? = null,

    @SerializedName("type")
    val type: TypeProfileDto? = null,
)
