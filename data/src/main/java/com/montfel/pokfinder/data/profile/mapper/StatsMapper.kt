package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.StatsDto
import com.montfel.pokfinder.domain.profile.model.Stats

fun StatsDto.toDomain(): Stats {
    return Stats(
        baseStat = baseStat,
        effort = effort,
        stat = stat?.toDomain(),
        min = if (stat?.toDomain()?.name == "HP") {
            baseStat?.times(2)?.plus(110)
        } else {
            baseStat?.times(2)
        },
        max = if (stat?.toDomain()?.name == "HP") {
            baseStat?.times(2)?.plus(204)
        } else {
            baseStat?.times(2.2)?.plus(108.9)?.toInt()
        }
    )
}
