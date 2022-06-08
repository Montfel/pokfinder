package com.montfel.pokedex.data.dto

import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.Pokemon

data class PokemonProfileDto(
    val id: Int,
    val name: String,
//    val sprite: SpriteDto,
    val types: List<TypesDto>,
    val height: Int?,
    val weight: Int?,
    val baseExp: Int?,
    val abilities: List<AbilitiesDto>,
    val stats: List<StatsDto>,
//    val species: SpeciesDto,
//    val locationUrl: String
    val evolutionChain: List<SpeciesDto>?,
    val captureRate: Int?,
    val growthRate: String?,
    val eggGroups: List<String?>?,
    val flavorTexts: List<FlavorTextDto>?,
    val genderRate: Int?,
    val genera: List<GeneraDto>?,
    val hatchCounter: Int?,
    val baseHappiness: Int?

) : DtoMapper<Pokemon> {
    override fun toDomain() = Pokemon(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
//        image = sprite.other.officialArtwork.frontDefault,
        types = types.map { it.toDomain() },
        height = height?.toFloat()?.div(10),
        weight = weight?.toFloat()?.div(10),
        baseExp = baseExp,
        abilities = abilities,
        stats = stats,
        captureRate = captureRate,
        genderRate = genderRate,
        growthRate = growthRate,
        eggGroups = eggGroups
            ?.map { it?.replaceFirstChar { first -> first.uppercase() } }
            ?.sortedBy { it },
        flavorTexts = flavorTexts
            ?.filter { it.language.name == "en" || it.language.name == ("pt-BR") }
            ?.map {
                FlavorTextDto(
                    flavorText = it.flavorText.replace("\n", " "),
                    language = it.language
                )
            },
        hatchCounter = hatchCounter,
        evolutionChain = evolutionChain,
        baseHappiness = baseHappiness,
        genera = genera?.filter { it.language.name == "en" || it.language.name == ("pt-BR") }
    )
}
