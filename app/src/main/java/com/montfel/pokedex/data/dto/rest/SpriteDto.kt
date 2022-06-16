package com.montfel.pokedex.data.dto.rest

import com.google.gson.annotations.SerializedName

data class SpriteDto(
    @SerializedName("other")
    val other: OtherDto,
)
