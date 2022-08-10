package com.montfel.pokedex.domain.home.model

import com.montfel.pokedex.domain.profile.model.Types

data class PokemonHome(
    val id: Int,
    val name: String,
    val types: List<Types>,
    val image: String? = null
)
