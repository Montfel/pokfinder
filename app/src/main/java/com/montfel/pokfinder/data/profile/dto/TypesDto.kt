package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName

data class TypesDto(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: TypeProfileDto
)
