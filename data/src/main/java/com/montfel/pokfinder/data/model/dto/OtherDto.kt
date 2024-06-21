package com.montfel.pokfinder.data.model.dto

import com.google.gson.annotations.SerializedName

data class OtherDto(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDto? = null,
)
