package com.montfel.pokfinder.feature.profile.data.model.dto

import com.google.gson.annotations.SerializedName

data class GeneraDto(
    @SerializedName("genus")
    val name: String? = null,

    @SerializedName("language")
    val language: LanguageDto? = null,
)
