package com.montfel.pokfinder.domain.profile.model

data class Stats(
    val baseStat: Int,
    val effort: Int,
    val stat: Stat,
    val min: Int,
    val max: Int
)
