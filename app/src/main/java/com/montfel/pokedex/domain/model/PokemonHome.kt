package com.montfel.pokedex.domain.model

data class PokemonHome(
    val id: Int,
    val name: String,
    val types: List<Types>,
    val image: String? = null
)
