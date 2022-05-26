package com.montfel.pokedex.data.dto

import com.google.gson.annotations.SerializedName

data class GeneraDto(
    @SerializedName("genus")
    val genus: String,
    @SerializedName("language")
    val language: LanguageDto
)
