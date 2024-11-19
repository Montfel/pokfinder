package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.EggGroupDto
import com.montfel.pokfinder.feature.profile.data.model.dto.FlavorTextDto
import com.montfel.pokfinder.feature.profile.data.model.dto.GeneraDto
import com.montfel.pokfinder.feature.profile.data.model.dto.PokemonSpeciesDto
import com.montfel.pokfinder.feature.profile.domain.model.HatchCounter
import com.montfel.pokfinder.feature.profile.domain.model.PokemonSpecies

fun PokemonSpeciesDto.toPokemonSpecies(): PokemonSpecies {
    return PokemonSpecies(
        baseHappiness = baseHappiness,
        captureRate = captureRate,
        eggGroups = eggGroups
            ?.sortedBy(EggGroupDto::name)
            ?.map(EggGroupDto::toEggGroup),
        flavorTexts = flavorTexts
            ?.map(FlavorTextDto::toFlavorText)
            ?.filter { it.language == "en" || it.language == "pt-BR" },
        genderRate = genderRate,
        genera = genera
            ?.map(GeneraDto::toGenera)
            ?.filter { it.language == "en" || it.language == "pt-BR" },
        growthRate = growthRate?.toGrowthRate()?.name,
        hatchCounter = HatchCounter(
            cycles = hatchCounter,
            steps = hatchCounter?.plus(1)?.times(255)
        ),
        evolutionChainId = evolutionChain?.url
            ?.dropLastWhile { it == '/' }
            ?.takeLastWhile { it.isDigit() }
            ?.toInt(),
        gender = if (genderRate == null || genderRate == -1) {
            "Genderless"
        } else {
            "♂ ${(8 - genderRate.toFloat()).div(8).times(100)}% | " +
                    "♀ ${genderRate.toFloat().div(8).times(100)}% "
        }
    )
}
