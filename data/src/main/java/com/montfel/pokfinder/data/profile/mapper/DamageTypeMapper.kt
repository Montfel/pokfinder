package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.DamageTypeDto
import com.montfel.pokfinder.domain.profile.model.DamageType

fun DamageTypeDto.toDomain(): DamageType {
    return DamageType(
        name = name
    )
}
