package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.Genera
import com.montfel.pokedex.data.DtoMapper

data class GeneraDto(
    @SerializedName("genus")
    val name: String,

    @SerializedName("language")
    val language: LanguageDto

) : DtoMapper<Genera> {
    override fun toDomain() = Genera(
        name = name,
        language = language.name
    )
}
