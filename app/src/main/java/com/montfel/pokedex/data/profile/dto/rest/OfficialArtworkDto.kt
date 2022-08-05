package com.montfel.pokedex.data.profile.dto.rest

import com.google.gson.annotations.SerializedName

data class OfficialArtworkDto(
    @SerializedName("front_default")
    val frontDefault: String
)
