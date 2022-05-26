package com.montfel.pokedex.data.dto

import com.google.gson.annotations.SerializedName

data class FlavorTextDto(
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("language")
    val language: LanguageDto
)