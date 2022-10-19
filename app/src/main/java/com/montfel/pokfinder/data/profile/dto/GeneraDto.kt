package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.domain.profile.model.Genera
import com.montfel.pokfinder.data.DtoMapper

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
