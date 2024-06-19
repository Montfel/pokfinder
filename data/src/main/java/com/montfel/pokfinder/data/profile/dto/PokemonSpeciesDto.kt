package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesDto(
    @SerializedName("base_happiness")
    val baseHappiness: Int? = null,

    @SerializedName("capture_rate")
    val captureRate: Int? = null,

    @SerializedName("egg_groups")
    val eggGroups: List<EggGroupDto>? = null,

    @SerializedName("flavor_text_entries")
    val flavorTexts: List<FlavorTextDto>? = null,

    @SerializedName("gender_rate")
    val genderRate: Int? = null,

    @SerializedName("genera")
    val genera: List<GeneraDto>? = null,

    @SerializedName("growth_rate")
    val growthRate: GrowthRateDto? = null,

    @SerializedName("hatch_counter")
    val hatchCounter: Int? = null,

    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChainDto? = null,
)
