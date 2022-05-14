package com.montfel.pokedex.domain

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("height")
    val height: Int
)
