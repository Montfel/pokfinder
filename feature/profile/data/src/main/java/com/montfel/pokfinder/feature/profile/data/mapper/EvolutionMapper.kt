package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.core.network.EvolutionQuery
import com.montfel.pokfinder.feature.profile.domain.model.EvolutionChain
import com.montfel.pokfinder.feature.profile.domain.model.EvolutionDetail

fun EvolutionQuery.Data.toEvolutionChains(): List<EvolutionChain> {
    return sortEvolutionChain(
        speciesList = pokemon_v2_evolutionchain_by_pk?.pokemon_v2_pokemonspecies ?: emptyList()
    ).map { it ->
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
    }
}

fun sortEvolutionChain(speciesList: List<EvolutionQuery.Pokemon_v2_pokemonspecy>): List<EvolutionQuery.Pokemon_v2_pokemonspecy> {
    val evolutionsMap = speciesList.groupBy { it.evolves_from_species_id }

    val sortedList = mutableListOf<EvolutionQuery.Pokemon_v2_pokemonspecy>()

    fun traverseChain(parentId: Int?) {
        val children = evolutionsMap[parentId] ?: emptyList()

        for (child in children) {
            sortedList.add(child)
            traverseChain(child.id)
        }
    }

    traverseChain(null)

    return sortedList
}
