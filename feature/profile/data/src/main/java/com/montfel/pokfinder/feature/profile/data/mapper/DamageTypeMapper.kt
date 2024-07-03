package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.DamageTypeDto
import com.montfel.pokfinder.feature.profile.domain.model.DamageType

fun DamageTypeDto.toDamageType(): DamageType {
    return DamageType(
        name = name
    )
}
