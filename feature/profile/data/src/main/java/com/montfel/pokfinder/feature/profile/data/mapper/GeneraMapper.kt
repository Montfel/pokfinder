package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.GeneraDto
import com.montfel.pokfinder.feature.profile.domain.model.Genera

fun GeneraDto.toGenera(): Genera {
    return Genera(
        name = name,
        language = language?.name
    )
}
