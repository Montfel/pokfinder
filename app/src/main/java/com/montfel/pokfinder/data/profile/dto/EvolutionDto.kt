package com.montfel.pokfinder.data.profile.dto

import com.montfel.pokfinder.EvolutionQuery
import com.montfel.pokfinder.domain.profile.model.EvolutionChain
import com.montfel.pokfinder.domain.profile.model.EvolutionDetail

fun EvolutionQuery.Data.toDomain(): List<EvolutionChain> {
    return pokemon_v2_evolutionchain_by_pk?.pokemon_v2_pokemonspecies?.map {
        EvolutionChain(
            id = it.id,
            name = it.name.replaceFirstChar { first -> first.uppercase() },
            evolutionDetail = it.pokemon_v2_pokemonevolutions.map { detail ->
                val split = detail.pokemon_v2_evolutiontrigger?.name?.split("-")
                val first = split?.first()?.replaceFirstChar { it.uppercase() }
                val last = split?.last()

                EvolutionDetail(
                    minLevel = detail.min_level,
                    trigger = "$first $last",
                )
            }
        )
    } ?: emptyList()
}
