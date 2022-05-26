package com.montfel.pokedex.data.dto

import com.google.gson.annotations.SerializedName

data class TypeDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
