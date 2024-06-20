package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.dto.profile.PokemonProfileDto
import com.montfel.pokfinder.domain.profile.model.EV
import com.montfel.pokfinder.domain.profile.model.PokemonProfile

fun PokemonProfileDto.toDomain(): PokemonProfile {
    val abilities = abilities?.map { it.toDomain() }
    var abilitiesText = ""

    abilities?.forEach {
        abilitiesText += if (it.isHidden == true) {
            "\n${it.ability?.name} (hidden ability)"
        } else {
            "${it.slot}. ${it.ability?.name}"
        }
    }

    return PokemonProfile(
        id = id,
        name = name?.replaceFirstChar { it.uppercase() },
        image = sprite?.other?.officialArtwork?.frontDefault,
        types = types?.map { it.toDomain() },
        height = height?.toFloat()?.div(10),
        weight = weight?.toFloat()?.div(10),
        baseExp = baseExp,
        abilities = abilities,
        stats = stats?.map { it.toDomain() },
        ev = stats
            ?.filter { (it.effort ?: 0) > 0 }
            ?.map {
                EV(
                    name = it.stat?.toDomain()?.name,
                    effort = it.effort
                )
            },
        abilitiesText = abilitiesText
    )
}
