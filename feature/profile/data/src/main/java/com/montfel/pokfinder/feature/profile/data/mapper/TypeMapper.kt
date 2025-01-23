package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.core.database.model.dto.TypeDto
import com.montfel.pokfinder.core.common.domain.model.Type

fun TypeDto.toType(): Type {
    return Type(
        slot = slot,
        name = typeName?.name?.replaceFirstChar { it.uppercase() }
    )
}
