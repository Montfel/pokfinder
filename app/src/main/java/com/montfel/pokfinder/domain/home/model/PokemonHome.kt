package com.montfel.pokfinder.domain.home.model

import com.montfel.pokfinder.domain.profile.model.Types

data class PokemonHome(
    val id: Int,
    val name: String,
    val types: List<Types>,
    val image: String? = null
)
