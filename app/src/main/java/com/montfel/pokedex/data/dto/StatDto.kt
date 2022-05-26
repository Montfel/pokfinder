package com.montfel.pokedex.data.dto

import com.google.gson.annotations.SerializedName

data class StatDto(
    @SerializedName("name")
    val name: String
)
