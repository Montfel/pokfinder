package com.montfel.pokedex.domain.model

import com.montfel.pokedex.data.dto.AbilitiesDto
import com.montfel.pokedex.data.dto.StatsDto

data class Pokemon(
    val id: Long? = null,
    val name: String? = null,
    val image: String? = null,
    val types: List<Type> = emptyList(),
    val height: Float? = null,
    val weight: Float? = null,
    val baseExp: Int? = null,
    val abilities: List<AbilitiesDto> = emptyList(),
    val stats: List<StatsDto> = emptyList()
)
