package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.core.database.model.dto.TypeDto
import com.montfel.pokfinder.domain.profile.model.Type

fun TypeDto.toType(): Type {
    return Type(
        slot = slot,
        name = typeName?.name?.replaceFirstChar { it.uppercase() }
    )
}