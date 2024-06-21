package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.model.dto.PokemonProfileDto
import com.montfel.pokfinder.domain.profile.model.EV
import com.montfel.pokfinder.domain.profile.model.PokemonProfile

fun PokemonProfileDto.toPokemonProfile(): PokemonProfile {
    val abilities = abilities?.map { it.toAbility() }
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
        types = types?.map { it.toType() },
        height = height?.toFloat()?.div(10),
        weight = weight?.toFloat()?.div(10),
        baseExp = baseExp,
        abilities = abilities,
        stats = stats?.map { it.toStat() },
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
