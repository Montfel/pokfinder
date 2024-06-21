package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.model.dto.DamageTypeDto
import com.montfel.pokfinder.domain.profile.model.DamageType

fun DamageTypeDto.toDamageType(): DamageType {
    return DamageType(
        name = name
    )
}
