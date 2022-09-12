package com.montfel.pokedex.domain.profile.model

data class EvolutionChain(
    val id: Int,
    val name: String,
    val evolutionDetail: List<EvolutionDetail>,
)
