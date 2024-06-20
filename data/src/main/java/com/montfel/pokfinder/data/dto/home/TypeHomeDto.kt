package com.montfel.pokfinder.data.dto.home

import com.montfel.pokfinder.data.TypesQuery
import com.montfel.pokfinder.domain.profile.model.Type

fun TypesQuery.Pokemon_v2_type.toDomain(): Type {
    return Type(
        name = name.replaceFirstChar { it.uppercase() },
    )
}
