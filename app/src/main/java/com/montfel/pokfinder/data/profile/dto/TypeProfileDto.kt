package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.data.DtoMapper
import com.montfel.pokfinder.domain.profile.model.Type
import com.montfel.pokfinder.domain.AssetFromType

data class TypeProfileDto(
    @SerializedName("name")
    val name: String

) : DtoMapper<Type> {
    override fun toDomain() = Type(
        name = name.replaceFirstChar { it.uppercase() },
        assetFromType = AssetFromType.getAsset(name)
    )
}
