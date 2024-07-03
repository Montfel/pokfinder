package com.montfel.pokfinder.feature.home.domain.model

import com.montfel.pokfinder.core.common.domain.model.Type

data class PokemonHome(
    val id: Int,
    val name: String,
    val types: List<Type>?,
)
