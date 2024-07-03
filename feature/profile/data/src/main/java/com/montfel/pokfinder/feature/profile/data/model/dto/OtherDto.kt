package com.montfel.pokfinder.feature.profile.data.model.dto

import com.google.gson.annotations.SerializedName

data class OtherDto(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDto? = null,
)
