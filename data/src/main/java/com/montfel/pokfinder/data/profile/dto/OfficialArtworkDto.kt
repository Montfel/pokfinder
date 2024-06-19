package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName

data class OfficialArtworkDto(
    @SerializedName("front_default")
    val frontDefault: String
)
