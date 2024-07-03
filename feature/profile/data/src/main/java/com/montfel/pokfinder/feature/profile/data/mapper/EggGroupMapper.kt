package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.EggGroupDto
import com.montfel.pokfinder.feature.profile.domain.model.EggGroup

fun EggGroupDto.toEggGroup(): EggGroup {
    return EggGroup(
        name = name?.replaceFirstChar { it.uppercase() }
    )
}
