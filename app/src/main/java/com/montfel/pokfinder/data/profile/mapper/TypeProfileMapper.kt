package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.TypeProfileDto
import com.montfel.pokfinder.domain.AssetFromType
import com.montfel.pokfinder.domain.profile.model.Type

fun TypeProfileDto.toDomain(): Type {
    return Type(
        name = name.replaceFirstChar { it.uppercase() },
        assetFromType = AssetFromType.getAsset(name)
    )
}
