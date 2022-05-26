package com.montfel.pokedex.data.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.Pokemon

data class PokemonSpeciesProfileDto(
    @SerializedName("evolution_chain")
    val evolutionChain: Long,
    @SerializedName("capture_rate")
    val captureRate: Int,
    @SerializedName("growth_rate")
    val growthRate: GrowthRateDto,
    @SerializedName("egg_groups")
    val eggGroups: List<EggGroupDto>,
    @SerializedName("flavor_text_entries")
    val flavorText: List<FlavorTextDto>,
    @SerializedName("gender_rate")
    val genderRate: Int,
    @SerializedName("genera")
    val genera: List<GeneraDto>,
    @SerializedName("hatch_counter")
    val hatchCounter: Int

) : DtoMapper<Pokemon> {
    override fun toDomain() = Pokemon(
    )
}
