package com.montfel.pokedex.data.dto

import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.Generation
import com.montfel.pokedex.domain.model.Type

data class GenerationDto(
    val name: String,
    val id: List<Int>
) : DtoMapper<Generation> {
    private val split = name.split("-")
    val first = split.first().replaceFirstChar { it.uppercase() }
    val last = split.last().uppercase()

    override fun toDomain() = Generation(
        name = "$first $last",
        id = id
    )
}
