package com.montfel.pokfinder.feature.home.data.mapper

import com.montfel.pokfinder.feature.home.data.GenerationsQuery
import com.montfel.pokfinder.feature.home.domain.model.Generation

fun GenerationsQuery.Pokemon_v2_generation.toGeneration(): Generation {
    val split = name.split("-")
    val first = split.firstOrNull()?.replaceFirstChar { it.uppercase() }
    val last = split.lastOrNull()?.uppercase()

    return Generation(
        id = id,
        name = "$first $last",
        pokemonIds = pokemon_v2_pokemonspecies.map { it.id }
    )
}
