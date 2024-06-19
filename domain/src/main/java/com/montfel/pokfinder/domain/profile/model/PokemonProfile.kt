package com.montfel.pokfinder.domain.profile.model

data class PokemonProfile(
    val id: Int?,
    val name: String?,
    val image: String?,
    val types: List<Types>?,
    val height: Float?,
    val weight: Float?,
    val baseExp: Int?,
    val abilities: List<Abilities>?,
    val stats: List<Stats>?,
    val ev: List<EV>?,
    val abilitiesText: String?
)
