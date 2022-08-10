package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.HatchCounter
import com.montfel.pokedex.domain.profile.model.PokemonSpecies
import com.montfel.pokedex.data.DtoMapper

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
    val hatchCounter: Int

) : DtoMapper<PokemonSpecies> {
    override fun toDomain() = PokemonSpecies(
        baseHappiness = baseHappiness,
        captureRate = captureRate,
        eggGroups = eggGroups.sortedBy { it.name }.map { it.toDomain() },
        flavorTexts = flavorTexts
            .map { it.toDomain() }
            .filter { it.language == "en" || it.language == "pt-BR" },
        genderRate = genderRate,
        genera = genera
            .map { it.toDomain() }
            .filter { it.language == "en" || it.language == "pt-BR" },
        growthRate = growthRate.toDomain().name,
        hatchCounter = HatchCounter(
            cycles = hatchCounter,
            steps = hatchCounter.plus(1).times(255)
        )
    )
}
