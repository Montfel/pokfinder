package com.montfel.pokedex.domain.model

data class Pokemon(
    val id: Long? = null,
    val name: String? = null,
    val image: String? = null,
    val types: List<Type> = emptyList(),
    val height: Int? = null,
    val weight: Int? = null
)
