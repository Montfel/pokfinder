package com.montfel.pokedex.data.home.dto

import com.montfel.pokedex.TypesQuery
import com.montfel.pokedex.domain.profile.model.Type
import com.montfel.pokedex.domain.AssetFromType

fun TypesQuery.Pokemon_v2_type.toDomain() = Type(
    name = name.replaceFirstChar { it.uppercase() },
    assetFromType = AssetFromType.getAsset(name)
)
