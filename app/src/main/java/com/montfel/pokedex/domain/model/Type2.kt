package com.montfel.pokedex.domain.model

// TODO: Verificar se esse arquivo é necessário
data class Type2(
    val name: String,
    val typeEfficacies: List<TypeEfficacy2>? = emptyList()
)
