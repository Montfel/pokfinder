package com.montfel.pokfinder.feature.profile.domain.model

data class EvolutionChain(
    val id: Int,
    val name: String,
    val evolutionDetail: List<EvolutionDetail>,
)
