package com.montfel.pokfinder.feature.profile.data.model.dto

import com.google.gson.annotations.SerializedName

data class SpriteDto(
    @SerializedName("other")
    val other: OtherDto? = null,
)
