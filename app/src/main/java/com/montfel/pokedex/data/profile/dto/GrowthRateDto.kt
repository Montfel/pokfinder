package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.GrowthRate
import com.montfel.pokedex.helper.DtoMapper

data class GrowthRateDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String,

    ) : DtoMapper<GrowthRate> {
    override fun toDomain() = GrowthRate(
        name = name
            .split("-")
            .joinToString(separator = " ") { word ->
                word.replaceFirstChar { letter ->
                    letter.uppercase()
                }
            },
        url = url,
    )
}
