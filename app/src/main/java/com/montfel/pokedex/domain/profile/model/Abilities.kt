package com.montfel.pokedex.domain.profile.model

data class Abilities(
    val ability: Ability,
    val isHidden: Boolean,
    val slot: Int
)
