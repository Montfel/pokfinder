package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.dto.profile.TypeProfileDto
import com.montfel.pokfinder.domain.profile.model.Type

fun TypeProfileDto.toDomain(): Type {
    return Type(
        name = name?.replaceFirstChar { it.uppercase() },
    )
}
