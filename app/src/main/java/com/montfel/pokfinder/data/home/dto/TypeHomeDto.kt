package com.montfel.pokfinder.data.home.dto

import com.montfel.pokfinder.TypesQuery
import com.montfel.pokfinder.domain.AssetFromType
import com.montfel.pokfinder.domain.profile.model.Type

fun TypesQuery.Pokemon_v2_type.toDomain(): Type {
    return Type(
        name = name.replaceFirstChar { it.uppercase() },
        assetFromType = AssetFromType.getAsset(name)
    )
}
