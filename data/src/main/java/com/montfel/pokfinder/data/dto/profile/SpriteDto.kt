package com.montfel.pokfinder.data.dto.profile

import com.google.gson.annotations.SerializedName

data class SpriteDto(
    @SerializedName("other")
    val other: OtherDto? = null,
)
