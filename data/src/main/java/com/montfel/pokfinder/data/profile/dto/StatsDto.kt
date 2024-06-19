package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName

data class StatsDto(
    @SerializedName("base_stat")
    val baseStat: Int? = null,

    @SerializedName("effort")
    val effort: Int? = null,

    @SerializedName("stat")
    val stat: StatDto? = null,
)
