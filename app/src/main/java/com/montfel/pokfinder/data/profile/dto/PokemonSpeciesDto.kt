package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesDto(
    @SerializedName("base_happiness")
    val baseHappiness: Int,

    @SerializedName("capture_rate")
    val captureRate: Int,

    @SerializedName("egg_groups")
    val eggGroups: List<EggGroupDto>,

    @SerializedName("flavor_text_entries")
    val flavorTexts: List<FlavorTextDto>,

    @SerializedName("gender_rate")
    val genderRate: Int,

    @SerializedName("genera")
    val genera: List<GeneraDto>,

    @SerializedName("growth_rate")
    val growthRate: GrowthRateDto,

    @SerializedName("hatch_counter")
    val hatchCounter: Int,

    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChainDto
)
