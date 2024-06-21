package com.montfel.pokfinder.data.model.dto

import com.google.gson.annotations.SerializedName

data class FlavorTextDto(
    @SerializedName("flavor_text")
    val flavorText: String? = null,

    @SerializedName("language")
    val language: LanguageDto? = null,
)
