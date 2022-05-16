package com.montfel.pokedex.data.dto

import com.google.gson.annotations.SerializedName

data class AllPokemonsDto(
    @SerializedName("results")
    val results: List<ResultDto>
)
