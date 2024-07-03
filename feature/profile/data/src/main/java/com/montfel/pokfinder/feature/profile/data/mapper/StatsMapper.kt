package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.StatDto
import com.montfel.pokfinder.feature.profile.domain.model.Stat

fun StatDto.toStat(): Stat {
    val statName = when (stat?.name) {
        "hp" -> "HP"
        "attack" -> "Attack"
        "defense" -> "Defense"
        "special-attack" -> "Sp. Atk"
        "special-defense" -> "Sp. Def"
        "speed" -> "Speed"
        else -> "Unknown"
    }

    return Stat(
        baseStat = baseStat,
        effort = effort,
        name = statName,
        min = if (statName == "HP") {
            baseStat?.times(2)?.plus(110)
        } else {
            baseStat?.times(2)
        },
        max = if (statName == "HP") {
            baseStat?.times(2)?.plus(204)
        } else {
            baseStat?.times(2.2)?.plus(108.9)?.toInt()
        }
    )
}
