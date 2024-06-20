package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.dto.profile.EggGroupDto
import com.montfel.pokfinder.domain.profile.model.EggGroup

fun EggGroupDto.toDomain(): EggGroup {
    return EggGroup(
        name = name?.replaceFirstChar { it.uppercase() }
    )
}
