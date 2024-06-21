package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.model.dto.GrowthRateDto
import com.montfel.pokfinder.domain.profile.model.GrowthRate

fun GrowthRateDto.toGrowthRate(): GrowthRate {
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
