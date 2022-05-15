package com.montfel.pokedex.domain

data class Pokemon(
    val id: Long,
    val height: Int,
    val name: String,
    val image: String,
    val types: List<Type>
)
