package com.montfel.pokedex.data.profile.dto

data class SpeciesDto(
    val id: Int,
    val name: String,
    val minLevel: List<Int?>
)
