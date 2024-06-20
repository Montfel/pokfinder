package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.dto.profile.DamageTypeDto
import com.montfel.pokfinder.domain.profile.model.DamageType

fun DamageTypeDto.toDomain(): DamageType {
    return DamageType(
        name = name
    )
}
