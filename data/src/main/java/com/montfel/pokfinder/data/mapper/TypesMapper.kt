package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.dto.profile.TypesDto
import com.montfel.pokfinder.domain.profile.model.Types

fun TypesDto.toDomain(): Types {
    return Types(
        slot = slot,
        type = type?.toDomain()
    )
}
