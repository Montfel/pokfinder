package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.domain.profile.model.Stat
import com.montfel.pokfinder.data.DtoMapper

data class StatDto(
    @SerializedName("name")
    val name: String

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
        }
    )
}
