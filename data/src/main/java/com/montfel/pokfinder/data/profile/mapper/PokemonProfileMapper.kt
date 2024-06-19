package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.PokemonProfileDto
import com.montfel.pokfinder.domain.profile.model.EV
import com.montfel.pokfinder.domain.profile.model.PokemonProfile

fun PokemonProfileDto.toDomain(): PokemonProfile {
    return PokemonProfile(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        image = sprite.other.officialArtwork.frontDefault,
        types = types.map { it.toDomain() },
        height = height.toFloat().div(10),
        weight = weight.toFloat().div(10),
        baseExp = baseExp,
        abilities = abilities.map { it.toDomain() },
        stats = stats.map { it.toDomain() },
        ev = stats
            .filter { it.effort > 0 }
            .map {
                EV(
                    name = it.stat.toDomain().name,
                    effort = it.effort
                )
            }
    )
}
