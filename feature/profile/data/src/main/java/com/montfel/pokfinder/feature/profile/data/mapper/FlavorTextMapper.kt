package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.FlavorTextDto
import com.montfel.pokfinder.feature.profile.domain.model.FlavorText

fun FlavorTextDto.toFlavorText(): FlavorText {
    return FlavorText(
        flavorText = flavorText?.replace("\n", " "),
        language = language?.name
    )
}
