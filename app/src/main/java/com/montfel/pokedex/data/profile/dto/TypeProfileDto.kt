package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.data.DtoMapper
import com.montfel.pokedex.domain.profile.model.Type
import com.montfel.pokedex.domain.AssetFromType

data class TypeProfileDto(
    @SerializedName("name")
    val name: String

) : DtoMapper<Type> {
    override fun toDomain() = Type(
        name = name.replaceFirstChar { it.uppercase() },
        assetFromType = AssetFromType.getAsset(name)
    )
}
