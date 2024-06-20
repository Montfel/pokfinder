package com.montfel.pokfinder.data.dto.profile

import com.google.gson.annotations.SerializedName

data class OtherDto(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDto? = null,
)
