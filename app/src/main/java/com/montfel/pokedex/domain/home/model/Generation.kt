package com.montfel.pokedex.domain.home.model

data class Generation(
    val id: Int,
    val name: String,
    val pokemonId: List<Int>
)
