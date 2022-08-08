package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.FlavorText
import com.montfel.pokedex.helper.DtoMapper

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
