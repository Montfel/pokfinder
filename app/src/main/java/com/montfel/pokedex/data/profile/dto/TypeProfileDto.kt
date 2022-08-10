package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.helper.DtoMapper
import com.montfel.pokedex.domain.profile.model.TypeProfile
import com.montfel.pokedex.helper.AssetFromType

data class TypeProfileDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val id: String

) : DtoMapper<TypeProfile> {
    override fun toDomain() = TypeProfile(
        name = name.replaceFirstChar { it.uppercase() },
        id = id.removeSuffix("/").takeLastWhile { it.isDigit() },
        assetFromType = AssetFromType.getAsset(name.replaceFirstChar { it.uppercase() })
    )
}
