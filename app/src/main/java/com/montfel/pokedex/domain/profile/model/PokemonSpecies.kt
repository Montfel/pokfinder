package com.montfel.pokedex.domain.profile.model

data class PokemonSpecies(
    val baseHappiness: Int,
    val captureRate: Int,
    val eggGroups: List<EggGroup>,
    val flavorTexts: List<FlavorText>,
    val genderRate: Int,
    val genera: List<Genera>,
    val growthRate: String,
    val hatchCounter: HatchCounter,
    val evolutionChainId: Int
)
