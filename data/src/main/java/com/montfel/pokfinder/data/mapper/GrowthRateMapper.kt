package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.dto.profile.GrowthRateDto
import com.montfel.pokfinder.domain.profile.model.GrowthRate

fun GrowthRateDto.toDomain(): GrowthRate {
    return GrowthRate(
        name = name
            ?.split("-")
            ?.joinToString(separator = " ") { word ->
                word.replaceFirstChar { letter ->
                    letter.uppercase()
                }
            }
    )
}
