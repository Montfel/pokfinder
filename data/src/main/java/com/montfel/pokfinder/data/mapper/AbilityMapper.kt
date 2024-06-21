package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.model.dto.AbilityDto
import com.montfel.pokfinder.domain.profile.model.Ability

fun AbilityDto.toAbility(): Ability {
    return Ability(
        name = abilityName?.name?.replaceFirstChar { it.uppercase() },
        isHidden = isHidden,
        slot = slot
    )
}
