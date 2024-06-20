package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.dto.profile.AbilityDto
import com.montfel.pokfinder.domain.profile.model.Ability

fun AbilityDto.toDomain(): Ability {
    return Ability(
        name = name?.replaceFirstChar { it.uppercase() }
    )
}
