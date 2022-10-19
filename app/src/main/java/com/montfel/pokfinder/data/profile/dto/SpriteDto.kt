package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName

data class SpriteDto(
    @SerializedName("other")
    val other: OtherDto
)
