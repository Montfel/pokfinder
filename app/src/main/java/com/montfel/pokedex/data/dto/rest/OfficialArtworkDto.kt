package com.montfel.pokedex.data.dto.rest

import com.google.gson.annotations.SerializedName

data class OfficialArtworkDto(
    @SerializedName("front_default")
    val frontDefault: String
)
