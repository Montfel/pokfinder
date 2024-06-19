package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.StatDto
import com.montfel.pokfinder.domain.profile.model.Stat

fun StatDto.toDomain(): Stat {
    return Stat(
        name = when (name) {
            "hp" -> "HP"
            "attack" -> "Attack"
            "defense" -> "Defense"
            "special-attack" -> "Sp. Atk"
            "special-defense" -> "Sp. Def"
            "speed" -> "Speed"
            else -> "Unknown"
        }
    )
}
