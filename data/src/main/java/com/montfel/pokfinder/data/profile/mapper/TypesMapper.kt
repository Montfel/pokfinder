package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.TypesDto
import com.montfel.pokfinder.domain.profile.model.Types

fun TypesDto.toDomain(): Types {
    return Types(
        slot = slot,
        type = type?.toDomain()
    )
}
