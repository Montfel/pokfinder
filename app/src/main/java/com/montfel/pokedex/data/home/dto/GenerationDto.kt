package com.montfel.pokedex.data.home.dto

import com.montfel.pokedex.GenerationsQuery
import com.montfel.pokedex.domain.home.model.Generation

fun GenerationsQuery.Pokemon_v2_generation.toDomain(): Generation {
    val split = name.split("-")
    val first = split.first().replaceFirstChar { it.uppercase() }
    val last = split.last().uppercase()

    return Generation(
        name = "$first $last",
        id = pokemon_v2_pokemonspecies.map { it.id }
    )
}
