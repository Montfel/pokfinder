package com.montfel.pokfinder.data.dto.profile

import com.google.gson.annotations.SerializedName

data class GeneraDto(
    @SerializedName("genus")
    val name: String? = null,

    @SerializedName("language")
    val language: LanguageDto? = null,
)
