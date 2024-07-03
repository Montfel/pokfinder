package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.EggGroupDto
import com.montfel.pokfinder.domain.profile.model.EggGroup

fun com.montfel.pokfinder.feature.profile.data.model.dto.EggGroupDto.toEggGroup(): EggGroup {
    return EggGroup(
        name = name?.replaceFirstChar { it.uppercase() }
    )
}
