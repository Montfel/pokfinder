package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName

data class GeneraDto(
    @SerializedName("genus")
    val name: String,

    @SerializedName("language")
    val language: LanguageDto
)
