package com.montfel.pokedex.data.profile.dto

data class StatsDto(
    val baseStat: Int,
    val effort: Int,
    val name: String?,
    val min: Int,
    val max: Int
)
