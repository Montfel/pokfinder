package com.montfel.pokedex.domain.model

data class Type(
    val name: String,
    val typeEfficacies: List<TypeEfficacy>? = emptyList()
)
