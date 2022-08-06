package com.montfel.pokedex.domain.home.model

data class PokemonHome(
    val id: Int,
    val name: String,
    val types: List<TypesHome>,
    val image: String? = null
)
