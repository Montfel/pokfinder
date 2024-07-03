package com.montfel.pokfinder.feature.profile.domain.model

import com.montfel.pokfinder.core.common.domain.model.Type

data class PokemonProfile(
    val id: Int?,
    val name: String?,
    val image: String?,
    val types: List<Type>?,
    val height: Float?,
    val weight: Float?,
    val baseExp: Int?,
    val abilities: List<Ability>?,
    val stats: List<Stat>?,
    val ev: List<EV>?,
    val abilitiesText: String?
)
