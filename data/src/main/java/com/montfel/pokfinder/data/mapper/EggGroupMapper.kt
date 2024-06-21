package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.model.dto.EggGroupDto
import com.montfel.pokfinder.domain.profile.model.EggGroup

fun EggGroupDto.toEggGroup(): EggGroup {
    return EggGroup(
        name = name?.replaceFirstChar { it.uppercase() }
    )
}
