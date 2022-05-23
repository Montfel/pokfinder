package com.montfel.pokedex.domain.model

data class Pokemon(
    val id: Long,
    val name: String,
    val image: String,
    val types: List<Type>
)
