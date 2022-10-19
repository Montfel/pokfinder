package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.domain.profile.model.GrowthRate
import com.montfel.pokfinder.data.DtoMapper

data class GrowthRateDto(
    @SerializedName("name")
    val name: String

) : DtoMapper<GrowthRate> {
    override fun toDomain() = GrowthRate(
        name = name
            .split("-")
            .joinToString(separator = " ") { word ->
                word.replaceFirstChar { letter ->
                    letter.uppercase()
                }
            }
    )
}
