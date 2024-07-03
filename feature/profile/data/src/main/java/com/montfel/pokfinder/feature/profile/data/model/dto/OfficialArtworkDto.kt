package com.montfel.pokfinder.feature.profile.data.model.dto

import com.google.gson.annotations.SerializedName

data class OfficialArtworkDto(
    @SerializedName("front_default")
    val frontDefault: String? = null,
)
