package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName

data class OtherDto(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDto
)
