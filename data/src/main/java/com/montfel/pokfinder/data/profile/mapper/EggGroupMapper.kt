package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.EggGroupDto
import com.montfel.pokfinder.domain.profile.model.EggGroup

fun EggGroupDto.toDomain(): EggGroup {
    return EggGroup(
        name = name.replaceFirstChar { it.uppercase() }
    )
}
