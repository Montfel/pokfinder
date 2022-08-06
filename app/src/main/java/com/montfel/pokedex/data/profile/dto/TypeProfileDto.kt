package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.helper.DtoMapper
import com.montfel.pokedex.domain.profile.model.TypeProfile

data class TypeProfileDto(
    @SerializedName("name")
    val name: String?,

    @SerializedName("url")
    val url: String?

) : DtoMapper<TypeProfile> {
    override fun toDomain() = TypeProfile(
        name = name?.replaceFirstChar { it.uppercase() } ?: "",
        url = url ?: ""
    )
}
