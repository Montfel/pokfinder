package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.dto.profile.StatDto
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
