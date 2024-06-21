package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.model.dto.FlavorTextDto
import com.montfel.pokfinder.domain.profile.model.FlavorText

fun FlavorTextDto.toFlavorText(): FlavorText {
    return FlavorText(
        flavorText = flavorText?.replace("\n", " "),
        language = language?.name
    )
}
