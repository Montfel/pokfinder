package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.EvolutionQuery
import com.montfel.pokfinder.domain.profile.model.EvolutionChain
import com.montfel.pokfinder.domain.profile.model.EvolutionDetail

fun EvolutionQuery.Data.toEvolutionChains(): List<EvolutionChain> {
    return pokemon_v2_evolutionchain_by_pk?.pokemon_v2_pokemonspecies?.map { it ->
        EvolutionChain(
            id = it.id,
            name = it.name.replaceFirstChar { first -> first.uppercase() },
            evolutionDetail = it.pokemon_v2_pokemonevolutions.map { (minLevel, evolutionTrigger) ->
                val split = evolutionTrigger?.name?.split("-")
                val first = split?.firstOrNull()?.replaceFirstChar { it.uppercase() }
                val last = split?.lastOrNull()

                EvolutionDetail(
                    minLevel = minLevel,
                    trigger = "$first $last",
                )
            }
        )
    } ?: emptyList()
}
