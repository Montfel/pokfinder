package com.montfel.pokfinder.data.model.dto

import com.google.gson.annotations.SerializedName

data class StatDto(
    @SerializedName("base_stat")
    val baseStat: Int? = null,

    @SerializedName("effort")
    val effort: Int? = null,

    @SerializedName("stat")
    val stat: StatNameDto? = null,
)
