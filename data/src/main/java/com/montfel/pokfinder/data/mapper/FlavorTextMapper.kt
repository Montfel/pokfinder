package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.dto.profile.FlavorTextDto
import com.montfel.pokfinder.domain.profile.model.FlavorText

fun FlavorTextDto.toDomain(): FlavorText {
    return FlavorText(
        flavorText = flavorText?.replace("\n", " "),
        language = language?.name
    )
}
