package com.montfel.pokedex.domain.model

import com.montfel.pokedex.data.profile.dto.*

data class Pokemon(
    val id: Int,
    val name: String? = null,
    val image: String? = null,
    val types: List<Types> = emptyList(),
    val height: Float? = null,
    val weight: Float? = null,
    val baseExp: Int? = null,
    val abilities: List<AbilitiesDto> = emptyList(),
    val stats: List<StatsDto> = emptyList(),
    val captureRate: Int? = null,
    val genderRate: Int? = null,
    val growthRate: String? = null,
    val eggGroups: List<String?>? = emptyList(),
    val flavorTexts: List<FlavorTextDto>? = emptyList(),
    val hatchCounter: Int? = null,
    val evolutionChain: List<SpeciesDto>? = emptyList(),
    val baseHappiness: Int? = null,
    val genera: List<GeneraDto>? = emptyList()
)
