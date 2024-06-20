package com.montfel.pokfinder.data.dto.home

import com.montfel.pokfinder.data.GenerationsQuery
import com.montfel.pokfinder.domain.home.model.Generation

fun GenerationsQuery.Pokemon_v2_generation.toDomain(): Generation {
    val split = name.split("-")
    val first = split.first().replaceFirstChar { it.uppercase() }
    val last = split.last().uppercase()

    return Generation(
        id = id,
        name = "$first $last",
        pokemonId = pokemon_v2_pokemonspecies.map { it.id }
    )
}
