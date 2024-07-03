package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.FlavorTextDto
import com.montfel.pokfinder.domain.profile.model.FlavorText

fun com.montfel.pokfinder.feature.profile.data.model.dto.FlavorTextDto.toFlavorText(): FlavorText {
    return FlavorText(
        flavorText = flavorText?.replace("\n", " "),
        language = language?.name
    )
}
