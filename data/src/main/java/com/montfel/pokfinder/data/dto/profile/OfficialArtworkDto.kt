package com.montfel.pokfinder.data.dto.profile

import com.google.gson.annotations.SerializedName

data class OfficialArtworkDto(
    @SerializedName("front_default")
    val frontDefault: String? = null,
)
