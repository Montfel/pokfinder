package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.AbilityDto
import com.montfel.pokfinder.domain.profile.model.Ability

fun AbilityDto.toDomain(): Ability {
    return Ability(
        name = name.replaceFirstChar { it.uppercase() }
    )
}
