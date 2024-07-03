package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.GeneraDto
import com.montfel.pokfinder.domain.profile.model.Genera

fun com.montfel.pokfinder.feature.profile.data.model.dto.GeneraDto.toGenera(): Genera {
    return Genera(
        name = name,
        language = language?.name
    )
}
