package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.Stat
import com.montfel.pokedex.helper.DtoMapper

data class StatDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String,

    ) : DtoMapper<Stat> {
    override fun toDomain() = Stat(
        name = when (name) {
            "hp" -> "HP"
            "attack" -> "Attack"
            "defense" -> "Defense"
            "special-attack" -> "Sp. Atk"
            "special-defense" -> "Sp. Def"
            "speed" -> "Speed"
            else -> "Unknown"
        },
        url = url,
    )
}
