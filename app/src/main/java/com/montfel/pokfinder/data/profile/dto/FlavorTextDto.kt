package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.domain.profile.model.FlavorText
import com.montfel.pokfinder.data.DtoMapper

data class FlavorTextDto(
    @SerializedName("flavor_text")
    val flavorText: String,

    @SerializedName("language")
    val language: LanguageDto

) : DtoMapper<FlavorText> {
    override fun toDomain() = FlavorText(
        flavorText = flavorText.replace("\n", " "),
        language = language.name
    )
}
