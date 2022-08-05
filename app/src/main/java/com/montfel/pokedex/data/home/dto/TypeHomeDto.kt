package com.montfel.pokedex.data.home.dto

import com.montfel.pokedex.TypesQuery
import com.montfel.pokedex.domain.model.Type

fun TypesQuery.Pokemon_v2_type.toDomain() = Type(
    name = name.replaceFirstChar { it.uppercase() }
)
