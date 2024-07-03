package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.AbilityDto
import com.montfel.pokfinder.feature.profile.domain.model.Ability

fun AbilityDto.toAbility(): Ability {
    return Ability(
        name = abilityName?.name?.replaceFirstChar { it.uppercase() },
        isHidden = isHidden,
        slot = slot
    )
}
