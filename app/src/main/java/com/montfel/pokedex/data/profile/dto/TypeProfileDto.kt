package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.helper.DtoMapper
import com.montfel.pokedex.domain.profile.model.Type
import com.montfel.pokedex.helper.AssetFromType

data class TypeProfileDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val id: String

) : DtoMapper<Type> {
    override fun toDomain() = Type(
        name = name.replaceFirstChar { it.uppercase() },
        assetFromType = AssetFromType.getAsset(name.replaceFirstChar { it.uppercase() })
    )
}
