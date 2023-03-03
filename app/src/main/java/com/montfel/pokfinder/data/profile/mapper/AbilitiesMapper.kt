package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.AbilitiesDto
import com.montfel.pokfinder.domain.profile.model.Abilities

fun AbilitiesDto.toDomain(): Abilities {
    return Abilities(
        ability = ability.toDomain(),
        isHidden = isHidden,
        slot = slot
    )
}
