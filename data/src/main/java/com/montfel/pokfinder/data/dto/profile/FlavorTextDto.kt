package com.montfel.pokfinder.data.dto.profile

import com.google.gson.annotations.SerializedName

data class FlavorTextDto(
    @SerializedName("flavor_text")
    val flavorText: String? = null,

    @SerializedName("language")
    val language: LanguageDto? = null,
)
