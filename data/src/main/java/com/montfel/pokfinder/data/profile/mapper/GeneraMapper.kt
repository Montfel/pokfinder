package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.GeneraDto
import com.montfel.pokfinder.domain.profile.model.Genera

fun GeneraDto.toDomain(): Genera {
    return Genera(
        name = name,
        language = language?.name
    )
}
