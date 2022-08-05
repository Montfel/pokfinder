package com.montfel.pokedex.data.profile.dto.rest

import com.google.gson.annotations.SerializedName

data class SpriteDto(
    @SerializedName("other")
    val other: OtherDto,
)
