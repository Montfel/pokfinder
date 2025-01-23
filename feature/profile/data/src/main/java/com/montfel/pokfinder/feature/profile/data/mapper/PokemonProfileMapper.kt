package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.core.database.model.dto.TypeDto
import com.montfel.pokfinder.feature.profile.data.model.dto.AbilityDto
import com.montfel.pokfinder.feature.profile.data.model.dto.PokemonProfileDto
import com.montfel.pokfinder.feature.profile.data.model.dto.StatDto
import com.montfel.pokfinder.feature.profile.domain.model.EV
import com.montfel.pokfinder.feature.profile.domain.model.PokemonProfile

fun PokemonProfileDto.toPokemonProfile(): PokemonProfile {
    val abilities = abilities?.map(AbilityDto::toAbility)
    var abilitiesText = ""

    abilities?.forEach {
        abilitiesText += if (it.isHidden == true) {
            "\n${it.name} (hidden ability)"
        } else {
            "${it.slot}. ${it.name}"
        }
    }

    return PokemonProfile(
        id = id,
        name = name?.replaceFirstChar { it.uppercase() },
        image = sprite?.other?.officialArtwork?.frontDefault,
        types = types?.map(TypeDto::toType),
        height = height?.toFloat()?.div(10),
        weight = weight?.toFloat()?.div(10),
        baseExp = baseExp,
        abilities = abilities,
        stats = stats?.map(StatDto::toStat),
        ev = stats
            ?.filter { (it.effort ?: 0) > 0 }
            ?.map {
                EV(
                    name = it.toStat().name,
                    effort = it.effort
                )
            },
        abilitiesText = abilitiesText
    )
}
