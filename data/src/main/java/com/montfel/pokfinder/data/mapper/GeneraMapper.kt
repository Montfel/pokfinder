package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.dto.profile.GeneraDto
import com.montfel.pokfinder.domain.profile.model.Genera

fun GeneraDto.toDomain(): Genera {
    return Genera(
        name = name,
        language = language?.name
    )
}
