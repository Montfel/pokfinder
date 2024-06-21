package com.montfel.pokfinder.domain.home.model

data class Generation(
    val id: Int,
    val name: String,
    val pokemonIds: List<Int>
)
