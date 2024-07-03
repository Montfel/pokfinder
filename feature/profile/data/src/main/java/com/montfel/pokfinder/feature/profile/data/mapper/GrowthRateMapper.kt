package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.GrowthRateDto
import com.montfel.pokfinder.feature.profile.domain.model.GrowthRate

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
