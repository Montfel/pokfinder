package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.model.dto.GeneraDto
import com.montfel.pokfinder.domain.profile.model.Genera

fun GeneraDto.toGenera(): Genera {
    return Genera(
        name = name,
        language = language?.name
    )
}
