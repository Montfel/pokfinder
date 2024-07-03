package com.montfel.pokfinder.feature.home.domain.model

data class Generation(
    val id: Int,
    val name: String,
    val pokemonIds: List<Int>
)
