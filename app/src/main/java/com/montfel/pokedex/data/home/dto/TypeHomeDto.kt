package com.montfel.pokedex.data.home.dto

import com.montfel.pokedex.TypesQuery
import com.montfel.pokedex.domain.home.model.TypeHome
import com.montfel.pokedex.helper.AssetFromType

fun TypesQuery.Pokemon_v2_type.toDomain() = TypeHome(
    name = name.replaceFirstChar { it.uppercase() },
    assetFromType = AssetFromType.getAsset(name.replaceFirstChar { it.uppercase() })
)
